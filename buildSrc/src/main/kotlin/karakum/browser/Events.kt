package karakum.browser

import karakum.common.snakeToCamel

private const val EVENT = "Event"

private data class EventData(
    val name: String,
    val type: String,
) {
    val typeName: String = type.substringBefore("<")
}

private val ADDITIONAL_EVENTS = listOf(
    EventData(
        name = "webkitfullscreenchange",
        type = "Event",
    ),

    // TEMP
    EventData(
        name = "uncapturederror",
        type = "GPUUncapturedErrorEvent",
    ),

    // Payment
    EventData(
        name = "shippingaddresschange",
        type = "PaymentRequestUpdateEvent",
    ),
    EventData(
        name = "shippingoptionchange",
        type = "PaymentRequestUpdateEvent",
    ),
    EventData(
        name = "payerdetailchange",
        type = "PaymentRequestUpdateEvent",
    ),

    // Document
    EventData(
        name = "resume",
        type = "Event",
    ),
    // HTMLMediaElement
    EventData(
        name = "progress",
        type = "Event",
    ),
    EventData(
        name = "input",
        type = "InputEvent",
    ),
    EventData(
        name = "toggle",
        type = "ToggleEvent",
    ),
    EventData(
        name = "beforetoggle",
        type = "ToggleEvent",
    ),
    EventData(
        name = "success",
        type = "IDBVersionChangeEvent",
    ),
)

private val EXCLUDED = setOf(
    // deprecated
    "AudioProcessingEvent",
    "MediaRecorderErrorEvent",
    "TextEvent",
)

internal fun eventDeclarations(
    content: String,
    webWorkerContent: String,
    serviceWorkersContent: String,
): Pair<List<ConversionResult>, Set<String>> {
    val dataMap = EventDataMap(content + "\n\n" + webWorkerContent + "\n\n" + serviceWorkersContent)
    val results = sequenceOf(
        EventType(),
        EventHandler(),
        EventInstance(),
        EventTarget(),
        HasTargets(),
    )
        .plus(eventPlaceholders(content, EVENT_DATA, dataMap, strict = true))
        .toList()

    return results to dataMap.knownEventTypes
}

internal fun webWorkersEventDeclarations(
    webWorkersContent: String,
): List<ConversionResult> {
    val dataMap = EventDataMap(webWorkersContent)
    return eventPlaceholders(webWorkersContent, WEB_WORKER_EVENT_DATA, dataMap)
}

internal fun serviceWorkersEventDeclarations(
    content: String,
    serviceWorkersContent: String,
): List<ConversionResult> {
    val dataMap = EventDataMap(content + "\n\n" + serviceWorkersContent)
    return eventPlaceholders(serviceWorkersContent, SERVICE_WORKER_EVENT_DATA, dataMap)
}

private fun eventPlaceholders(
    source: String,
    data: List<EventInfo>,
    dataMap: EventDataMap,
    strict: Boolean = false,
): List<ConversionResult> {
    if (strict) {
        val eventNames = Regex("""interface ([\w\d]+Event) extends """)
            .findAll(source)
            .map { it.groupValues[1] }
            .filter { it !in EXCLUDED }
            .toList()

        val knownEventNames = data.map { it.name }.toSet()
        val unknownEventNames = eventNames.filter { it !in knownEventNames }

        check(unknownEventNames.isEmpty()) {
            "Unknown events:\n" + unknownEventNames.joinToString("\n")
        }
    }

    return data.flatMap { info ->
        val types = eventTypes(
            eventName = info.name,
            pkg = info.pkg,
            types = dataMap.getEventTypes(info.name),
        )

        val typesName = if (types.isNotEmpty()) {
            "${info.name}Types"
        } else null

        // TEMP
        if (info.name == "GPUUncapturedErrorEvent")
            return@flatMap types.asSequence()

        event(
            source = source,
            name = info.name,
            pkg = info.pkg,
            typesName = typesName,
        ).plus(types)
    }
}

private fun event(
    source: String,
    name: String,
    pkg: String,
    typesName: String?,
): Sequence<ConversionResult> {
    var initName = "${name}Init" +
            (if (name == "CustomEvent" || name == "MessageEvent") "<D = any>" else "")

    var initSource = source
        .substringAfter("\ninterface $initName ", "")
        .substringBefore(";\n}\n")

    if ("{\n}" in initSource)
        initSource = initSource.substringBefore("}")

    var initMembers: String? = null
    var initBody = if (initSource.isNotEmpty()) {
        val parentDeclaration = initSource
            .substringBefore("{\n")
            .replace("extends ", ":\n")

        val typeProvider = TypeProvider(initName)

        val membersSource = initSource
            .substringAfter("{\n")
            .trimIndent()

        val members = if (membersSource.isNotEmpty()) {
            membersSource
                .splitToSequence(";\n")
                // TODO: move to patches?
                .map { "readonly $it" }
                .mapNotNull { convertMember(it, typeProvider) }
                .joinToString("\n")
        } else ""

        initMembers = members

        val declaration = initName
            .replace("<D = any>", "<out D>") +
                " $parentDeclaration"

        sequenceOf(
            "@JsPlainObject",
            "external interface $declaration {",
            members,
            "}",
        ).joinToString("\n")
    } else ""

    val eventSource = source
        // MessageEvent
        .replace("<D = any>", "")
        .let {
            when (name) {
                EVENT -> " {\n" + it.substringAfter("\ninterface $name {\n")
                else -> it.substringAfter("\ninterface $name extends ")
            }
        }
        .substringBefore(";\n}\n")

    val eventParent = eventSource.substringBefore(" {\n")
    val eventIsInitLike = initBody.isNotEmpty()

    val eventParents = listOfNotNull(
        eventParent.takeIf { name != EVENT },
    )
    val eventParentDeclaration = if (eventParents.isNotEmpty()) {
        ": " + eventParents.joinToString(",\n")
    } else ""

    val typeProvider = TypeProvider(name)

    val eventMembers = eventSource.substringAfter(" {\n")
        .trimIndent()
        .splitToSequence(";\n")
        .mapNotNull { convertMember(it, typeProvider) }
        .joinToString("\n")
        // Event
        .replace("val type: String", "    // val type: String")
        .let {
            if (eventIsInitLike && name != "BlobEvent") {
                val modifier = when (name) {
                    EVENT -> "open"
                    // "BlobEvent" -> ""
                    else -> "override"
                }

                val resultType = initName.replace("<D = any>", "<D>")

                it + "\n\n" + """
                @JsAlias(THIS)
                $modifier fun asInit(): $resultType
                """.trimIndent()
            } else it
        }

    val eventClassBody = source
        .substringAfter("\ndeclare var $name: {\n")
        .substringBefore(";\n};")
        .trimIndent()
        .removePrefix("prototype: $name;\n")

    val constructorSource = eventClassBody
        .removePrefix("new(")
        .removePrefix("new<T>(")
        .substringBefore("): $name")

    val eventConstructor = if (constructorSource.isNotEmpty()) {
        val withDataSupport = when (name) {
            "CustomEvent",
            "MessageEvent",
                -> true

            else -> false
        }

        val typeParameter = if (withDataSupport) "<D>" else ""
        val eventType = "EventType<$name$typeParameter>"

        val eventParameters = constructorSource
            .split(", ")
            .map { p ->
                if ("?: " in p) {
                    p.replace("?: ", ": ") + " = definedExternally"
                } else {
                    val typeModifier = if (name == EVENT) "open" else "override"
                    val typeDeclaration = "$typeModifier val type: $eventType"

                    p.replace("type: string", typeDeclaration)
                        .replace(": string", ": String")
                }
            }
            .map {
                if ("InitDict: " in it || "Init: " in it) {
                    "init: " + it.substringAfter(": ")
                } else it
            }
            .joinToString(",\n")

        "(\n$eventParameters\n)"
    } else ""

    val companionSource = eventClassBody
        .substringAfter("\n", "")

    val companionMembers = if (companionSource.isNotEmpty()) {
        companionSource
            .splitToSequence(";\n")
            .mapNotNull { convertMember(it, typeProvider) }
            .joinToString("\n")
    } else null

    val companionParentDeclaration = if (typesName != null) {
        ": $typesName"
    } else ""

    val companion = if (companionMembers != null) {
        "companion object $companionParentDeclaration {\n$companionMembers\n}"
    } else "companion object $companionParentDeclaration"

    val modifier = if (eventConstructor.isNotEmpty()) "open" else "sealed"
    val typeParameters = when (name) {
        "CustomEvent",
        "MessageEvent",
            -> "<out D>"

        else -> ""
    }

    var eventBody = """  
    $modifier external class $name$typeParameters $eventConstructor $eventParentDeclaration {
        $eventMembers
    
        $companion
    }
    """.trimIndent()

    eventBody = eventBody
        .withComment(
            fullSource = source,
            source = "interface $name ",
        )
        // CustomEvent, MessageEvent
        .withComment(
            fullSource = source,
            source = "interface $name<",
        )

    if (name == "MediaQueryListEvent") {
        initBody = initBody.applyMediaQueryPatch()
        eventBody = eventBody.applyMediaQueryPatch()
    }

    initName = initName.substringBefore("<")
    val initFactoryBody = when (name) {
        "CustomEvent",
        "MessageEvent",
            -> {
            val members = requireNotNull(initMembers)
                .substringBefore("\n")

            val parameters = members
                .splitToSequence("\n")
                .map { it.substringAfter(" ") + "," }
                .joinToString("\n")

            val parameterNames = members
                .splitToSequence("\n")
                .map { it.substringAfter(" ") }
                .map { it.substringBefore(":") }
                .toList()

            """
            fun <D> $initName(
                $parameters
            ): $initName<D> =
                jso {
                    ${
                parameterNames.joinToString("\n") {
                    "asDynamic().$it = $it"
                }
            }
                }
            """.trimIndent()
        }

        else -> ""
    }

    return sequenceOf(
        ConversionResult(
            name = initName,
            body = initBody,
            pkg = pkg,
        ),
        ConversionResult(
            name = "$initName.factory",
            body = initFactoryBody,
            pkg = pkg,
        ),
        ConversionResult(
            name = name,
            body = eventBody,
            pkg = pkg,
        ),
    ).filter { it.body.isNotEmpty() }
}

private class EventDataMap(
    content: String,
) {
    private val map = Regex("""interface .+?EventMap \{\n    "[\s\S]+?\n\}""")
        .findAll(content)
        .flatMap { parseEvents(it.value) }
        .filter { it.name != "orientationchange" }
        .filter { it.name != "pushsubscriptionchange" }
        .plus(ADDITIONAL_EVENTS)
        .distinct()
        .groupBy { it.typeName }
        .filter { it.key !in EXCLUDED }

    val knownEventTypes: Set<String> = map.values
        .flatMap { it.map(EventData::name) }
        .toSet()

    fun getEventTypes(
        eventName: String,
    ): List<String>? {
        val data = map[eventName]
            ?: return null

        return data.map { it.name }
    }
}

private fun eventTypes(
    eventName: String,
    pkg: String,
    types: List<String>?,
): List<ConversionResult> {
    types ?: return emptyList()

    val typesName = "${eventName}Types"
    val eventType = when (eventName) {
        "MessageEvent",
            -> "$eventName<*>"

        else -> eventName
    }

    val members = types
        .sorted()
        .joinToString("\n\n") { name ->
            val memberName = EVENT_CORRECTION_MAP
                .getOrDefault(name, name)
                .uppercase()

            """
            @JsValue("$name")
            val $memberName: $EVENT_TYPE<$eventType>
            """.trimIndent()
        }

    val body = """
    sealed external class $typesName :
        ${typesName}_deprecated {

        $members
    }
    """.trimIndent()

    val deprecatedTypeParameters = when (eventName) {
        "MessageEvent",
            -> "<D>"

        else -> ""
    }

    val deprecatedEventType = eventName + deprecatedTypeParameters

    val deprecatedMembers = types
        .sorted()
        .joinToString("\n\n") { name ->
            val memberName = EVENT_CORRECTION_MAP
                .getOrDefault(name, name)
                .uppercase()

            val deprecatedMemberName = EVENT_CORRECTION_MAP
                .getOrDefault(name, name)
                .snakeToCamel()

            """
            @Deprecated(
                message = "Legacy event type declaration. Use type constant instead!",
                replaceWith = ReplaceWith("$eventName.$memberName"),
            )
            @JsValue("$name")
            fun $deprecatedTypeParameters $deprecatedMemberName(): $EVENT_TYPE<$deprecatedEventType>
            """.trimIndent()
        }

    val deprecatedBody = """
    sealed external class ${typesName}_deprecated {

        $deprecatedMembers
    }
    """.trimIndent()

    return listOf(
        ConversionResult(
            name = "${eventName}.types",
            body = body,
            pkg = pkg,
        ),
        ConversionResult(
            name = "${eventName}.types.deprecated",
            body = deprecatedBody,
            pkg = pkg,
        ),
    )
}

private fun parseEvents(
    source: String,
): Sequence<EventData> {
    return source.splitToSequence("\n")
        .mapNotNull { parseEventData(it) }
}

private fun parseEventData(
    source: String,
): EventData? {
    if (!source.endsWith(";")) return null

    val (name, type) = source
        .removeSurrounding("    \"", ";")
        .split("\": ", "<")

    return EventData(
        name = name,
        type = type,
    )
}
