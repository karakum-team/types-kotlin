package karakum.browser

import karakum.common.snakeToCamel

private const val EVENT = "Event"

private val PACKAGE_MAP = mapOf(
    "AbortSignal" to "web.abort",
    "Animation" to "web.animations",
    "AudioScheduledSourceNode" to "web.audio",
    "AudioWorkletNode" to "web.audio",
    "BaseAudioContext" to "web.audio",
    "DedicatedWorkerGlobalScope" to "web.workers",
    "Document" to "web.dom",
    "Element" to "web.dom",
    "EventSource" to "web.sse",
    "FontFaceSet" to "web.fonts",
    "GlobalEventHandlers" to "web.dom",
    "HTMLMediaElement" to "web.html",
    "HTMLVideoElement" to "web.html",
    "IDBDatabase" to "web.idb",
    "IDBOpenDBRequest" to "web.idb",
    "IDBRequest" to "web.idb",
    "IDBTransaction" to "web.idb",
    "MediaDevices" to "web.media.devices",
    "MediaKeySession" to "web.media.key",
    "MediaRecorder" to "web.media.recorder",
    "MediaSource" to "web.media.source",
    "MediaStreamTrack" to "web.media.streams",
    "MIDIAccess" to "web.midi",
    "MIDIInput" to "web.midi",
    "MIDIPort" to "web.midi",
    "Notification" to "web.notifications",
    "OffscreenCanvas" to "web.canvas",
    "PaymentRequest" to "web.payment",
    "Performance" to "web.performance",
    "PermissionStatus" to "web.permissions",
    "PictureInPictureWindow" to "web.pip",
    "RTCDataChannel" to "web.rtc",
    "RTCDtlsTransport" to "web.rtc",
    "RTCIceTransport" to "web.rtc",
    "RTCPeerConnection" to "web.rtc",
    "RTCSctpTransport" to "web.rtc",
    "RemotePlayback" to "web.remoteplayback",
    "ScreenOrientation" to "web.screen",
    "ServiceWorker" to "web.serviceworker",
    "ServiceWorkerContainer" to "web.serviceworker",
    "ServiceWorkerRegistration" to "web.serviceworker",
    "ShadowRoot" to "web.components",
    "SourceBuffer" to "web.media.source",
    "SourceBufferList" to "web.media.source",
    "SpeechSynthesis" to "web.speech",
    "TextTrack" to "web.vtt",
    "TextTrackCue" to "web.vtt",
    "TextTrackList" to "web.vtt",
    "VideoDecoder" to "web.codecs",
    "VideoEncoder" to "web.codecs",
    "VisualViewport" to "web.window",
    "WakeLockSentinel" to "web.wakelock",
    "WebSocket" to "web.sockets",
    "Window" to "web.window",
    "WindowEventHandlers" to "web.window",
    "XMLHttpRequest" to "web.xhr",

    "ServiceWorkerGlobalScope" to "web.serviceworker",
    "WorkerGlobalScope" to "web.workers",
)

private data class EventData(
    val name: String,
    val type: String,
    val pkg: String,
) {
    val typeName: String = type.substringBefore("<")
}

private val ADDITIONAL_EVENTS = listOf(
    EventData(
        name = "webkitfullscreenchange",
        type = "Event",
        pkg = "web.dom", // ???
    ),

    // TEMP
    EventData(
        name = "uncapturederror",
        type = "GPUUncapturedErrorEvent",
        pkg = "web.gpu",
    ),

    // Document
    EventData(
        name = "resume",
        type = "Event",
        pkg = "web.dom",
    ),
    // HTMLMediaElement
    EventData(
        name = "progress",
        type = "Event",
        pkg = "web.html",
    ),
    EventData(
        name = "input",
        type = "InputEvent",
        pkg = "web.uievents",
    ),
    EventData(
        name = "toggle",
        type = "ToggleEvent",
        pkg = "web.uievents",
    ),
    EventData(
        name = "beforetoggle",
        type = "ToggleEvent",
        pkg = "web.uievents",
    ),
    EventData(
        name = "success",
        type = "IDBVersionChangeEvent",
        pkg = "web.idb",
    ),
)

private val EXCLUDED = setOf(
    // deprecated
    "AudioProcessingEvent",
    "MediaRecorderErrorEvent",
    "MutationEvent",
    "TextEvent",
)

internal fun eventDeclarations(
    content: String,
    webWorkerContent: String,
    serviceWorkersContent: String,
): Pair<List<ConversionResult>, Set<String>> {
    val dataMap = EventDataMap(content + "\n\n" + webWorkerContent + "\n\n" + serviceWorkersContent)
    val results = eventTypes(dataMap)
        .asSequence()
        .plus(EventType())
        .plus(EventHandler())
        .plus(EventInstance())
        .plus(EventTarget())
        .plus(HasTargets())
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
        val eventType = "EventType<$name$typeParameter, EventTarget>"

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
    private val DEFAULT_EVENT_TYPES = listOf(
        "abort",
        "cancel",
        "change",
        "close",
        "closing",
        "complete",
        "error",
        "open",
        "success",
    )

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

        val types = data.map { it.name }
        return when (eventName) {
            EVENT -> DEFAULT_EVENT_TYPES.filter { it in types }
            else -> types
        }
    }

    fun getDefaultEventTypes(): List<EventData> =
        map.getValue("Event")
            .filter { it.name !in DEFAULT_EVENT_TYPES }
}

private fun eventTypes(
    dataMap: EventDataMap,
): List<ConversionResult> =
    dataMap.getDefaultEventTypes()
        .groupBy { it.pkg }
        .values
        .map { items -> eventTypes(items) }

private fun eventTypes(
    eventName: String,
    pkg: String,
    types: List<String>?,
): List<ConversionResult> {
    types ?: return emptyList()

    val typesName = "${eventName}Types"
    val typeParameters = when (eventName) {
        "MessageEvent",
        -> "<D, C : EventTarget>"

        else -> "<C : EventTarget>"
    }

    val eventType = when (eventName) {
        "MessageEvent",
        -> "$eventName<D>"

        else -> eventName
    }

    val members = types
        .sorted()
        .joinToString("\n\n") { name ->
            val memberName = EVENT_CORRECTION_MAP
                .getOrDefault(name, name)
                .snakeToCamel()

            """
            @JsValue("$name")
            fun $typeParameters $memberName(): $EVENT_TYPE<$eventType, C>
            """.trimIndent()
        }

    val body = """
    sealed external class $typesName {

        $members
    }
    """.trimIndent()

    return listOf(
        ConversionResult(
            name = "${eventName}.types",
            body = body,
            pkg = pkg,
        )
    )
}

private fun eventTypes(
    items: List<EventData>,
): ConversionResult {
    val firstItem = items.first()
    val typeName = firstItem.typeName

    val body = items.asSequence()
        .map { it.name }
        .sorted()
        .joinToString("\n\n") { name ->
            val memberName = EVENT_CORRECTION_MAP
                .getOrDefault(name, name)
                .snakeToCamel()

            """
            inline fun <C : EventTarget> $typeName.Companion.$memberName() : $EVENT_TYPE<$typeName, C> =
                $EVENT_TYPE("$name")
            """.trimIndent()
        }

    val pkg = firstItem.pkg
        .takeIf { it.isNotEmpty() }

    return ConversionResult(
        name = "$typeName.types",
        body = body,
        pkg = pkg,
    )
}

private fun parseEvents(
    source: String,
): Sequence<EventData> {
    val mapId = source.substringBefore("EventMap")
        .substringAfterLast(" ")

    return source.splitToSequence("\n")
        .mapNotNull { parseEventData(it, mapId) }
}

private fun parseEventData(
    source: String,
    mapId: String,
): EventData? {
    if (!source.endsWith(";")) return null

    val (name, type) = source
        .removeSurrounding("    \"", ";")
        .split("\": ", "<")

    val pkg = if (type == "Event") {
        PACKAGE_MAP.getValue(mapId)
    } else ""

    return EventData(
        name = name,
        type = type,
        pkg = pkg,
    )
}
