package karakum.browser

private const val EVENT = "Event"

private val PACKAGE_MAP = mapOf(
    "AbortSignal" to "web.abort",
    "Animation" to "web.animations",
    "AudioScheduledSourceNode" to "web.audio",
    "AudioWorkletNode" to "web.audio",
    "BaseAudioContext" to "web.audio",
    "Document" to "web.dom",
    "Element" to "web.dom",
    "EventSource" to "web.sse",
    "FontFaceSet" to "web.fonts",
    "GlobalEventHandlers" to "web.dom",
    "HTMLMediaElement" to "web.html",
    "HTMLVideoElement" to "web.html",
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
    "RTCDataChannel" to "webrtc",
    "RTCDtlsTransport" to "webrtc",
    "RTCIceTransport" to "webrtc",
    "RTCPeerConnection" to "webrtc",
    "RTCSctpTransport" to "webrtc",
    "RemotePlayback" to "web.remoteplayback",
    "ScreenOrientation" to "web.screen",
    "ServiceWorker" to "web.serviceworker",
    "ServiceWorkerContainer" to "web.serviceworker",
    "ServiceWorkerRegistration" to "web.serviceworker",
    "ShadowRoot" to "web.html",
    "SourceBuffer" to "web.media.source",
    "SourceBufferList" to "web.media.source",
    "SpeechSynthesis" to "web.speech",
    "TextTrack" to "webvtt",
    "TextTrackCue" to "webvtt",
    "TextTrackList" to "webvtt",
    "VideoDecoder" to "web.codecs",
    "VideoEncoder" to "web.codecs",
    "VisualViewport" to "web.window",
    "WakeLockSentinel" to "web.wakelock",
    "WebSocket" to "websockets",
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
)

private val EXCLUDED = setOf(
    // deprecated
    "AudioProcessingEvent",
    "MediaRecorderErrorEvent",
    "MutationEvent",
)

internal fun eventDeclarations(
    content: String,
    webworkerContent: String,
): List<ConversionResult> {
    val dataMap = EventDataMap(content + "\n\n" + webworkerContent)
    return eventTypes(dataMap)
        .plus(EventType())
        .plus(eventPlaceholders(content, EVENT_DATA, dataMap, strict = true))
}

internal fun workerEventDeclarations(
    content: String,
    webworkerContent: String,
): List<ConversionResult> {
    val dataMap = EventDataMap(content + "\n\n" + webworkerContent)
    return eventPlaceholders(webworkerContent, WORKER_EVENT_DATA, dataMap)
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

    return data
        .map { info ->
            event(
                source = source,
                name = info.name,
                pkg = info.pkg,
                types = dataMap.getEventTypes(info.name),
            )
        }
}

private fun event(
    source: String,
    name: String,
    pkg: String,
    types: List<String>?,
): ConversionResult {
    val initName = "${name}Init" +
            (if (name == "CustomEvent" || name == "MessageEvent") "<T = any>" else "")

    var initSource = source
        .substringAfter("\ninterface $initName ", "")
        .substringBefore(";\n}\n")

    if ("{\n}" in initSource)
        initSource = initSource.substringBefore("}")

    val initBody = if (initSource.isNotEmpty()) {
        val parentDeclaration = initSource
            .substringBefore("{\n")
            .replace("extends ", ": ")

        val typeProvider = TypeProvider(initName)

        val membersSource = initSource
            .substringAfter("{\n")
            .trimIndent()

        val members = if (membersSource.isNotEmpty()) {
            membersSource
                .splitToSequence(";\n")
                .mapNotNull { convertMember(it, typeProvider) }
                .joinToString("\n")
        } else ""

        val declaration = initName
            .replace("<T = any>", "<T>") +
                " $parentDeclaration"

        "external interface $declaration {\n$members\n}"
    } else ""

    val eventSource = source
        // MessageEvent
        .replace("<T = any>", "")
        // ProgressEvent
        .replace("<T extends EventTarget = EventTarget>", "")
        .let {
            when (name) {
                EVENT -> " {\n" + it.substringAfter("\ninterface $name {\n")
                else -> it.substringAfter("\ninterface $name extends ")
            }
        }
        .substringBefore(";\n}\n")

    val eventParent = eventSource.substringBefore(" {\n")
    val eventParentDeclaration = if (name != EVENT) ": $eventParent" else ""

    val typeProvider = TypeProvider(name)

    val eventMembers = eventSource.substringAfter(" {\n")
        .trimIndent()
        .splitToSequence(";\n")
        .mapNotNull { convertMember(it, typeProvider) }
        .joinToString("\n")
        // Event
        .replace("val type: String", "    // val type: String")
        .replace("val target: EventTarget?", "open val target: EventTarget?")
        // ProgressEvent
        .replace("val target: T?", "override val target: T?")

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
        constructorSource
            .split(", ")
            .map { p ->
                if ("?: " in p) {
                    p.replace("?: ", ": ") + " = definedExternally"
                } else {
                    val typeParameter = when (name) {
                        "CustomEvent",
                        "MessageEvent",
                        "ProgressEvent",
                        -> "<T>"

                        else -> ""
                    }

                    val typeModifier = if (name == EVENT) "open" else "override"
                    val typeDeclaration = "$typeModifier val type: EventType<$name$typeParameter>"

                    p.replace("type: string", typeDeclaration)
                        .replace(": string", ": String")
                }
            }
            .map {
                if ("InitDict: " in it || "Init: " in it) {
                    "init: " + it.substringAfter(": ")
                } else it
            }
            .joinToString(",\n", "(\n", "\n)")
    } else ""

    val companionSource = eventClassBody
        .substringAfter("\n", "")

    val companionMembers = if (companionSource.isNotEmpty()) {
        companionSource
            .splitToSequence(";\n")
            .mapNotNull { convertMember(it, typeProvider) }
            .joinToString("\n")
    } else null

    val companion = if (companionMembers != null || types != null) {
        val typeMembers = eventTypeMembers(name, types)

        val members = sequenceOf(companionMembers, typeMembers)
            .filterNotNull()
            .joinToString("\n\n")

        "companion object {\n$members\n}"
    } else "companion object"

    val modifier = if (eventConstructor.isNotEmpty()) "open" else "sealed"
    val typeParameters = when (name) {
        "CustomEvent" -> "<out T>"
        "MessageEvent" -> "<out T>"
        "ProgressEvent" -> "<out T : EventTarget>"
        else -> ""
    }

    var eventBody = """  
    $modifier external class $name$typeParameters $eventConstructor $eventParentDeclaration {
        $eventMembers
    
        $companion
    }
    """.trimIndent()

    if (name == "TouchEvent")
        eventBody = "@JsName(\"globalThis.$name\")\n$eventBody"

    eventBody = eventBody
        .withComment(
            fullSource = source,
            source = "interface $name extends "
        )
        // ProgressEvent
        .withComment(
            fullSource = source,
            source = "interface $name<"
        )

    var body = sequenceOf(
        initBody,
        eventBody,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (name == "MediaQueryListEvent")
        body = body.applyMediaQueryPatch()

    return ConversionResult(
        name = name,
        body = body,
        pkg = pkg,
    )
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

    fun getEventTypes(
        eventName: String,
    ): List<String>? {
        if (eventName == EVENT)
            return null

        val data = map[eventName]
            ?: return null

        return data.map { it.name }
    }


    fun getDefaultEventTypes(): List<EventData> =
        map.getValue("Event")
}

private fun eventTypes(
    dataMap: EventDataMap,
): List<ConversionResult> =
    dataMap.getDefaultEventTypes()
        .groupBy { it.pkg }
        .values
        .map { items -> eventTypes(items) }

private fun eventTypeMembers(
    eventName: String,
    types: List<String>?,
): String? {
    types ?: return null

    val eventType = when (eventName) {
        "MessageEvent",
        "ProgressEvent",
        -> "$eventName<*>"

        else -> eventName
    }

    return types
        .sorted()
        .joinToString("\n\n") { name ->
            val memberName = EVENT_CORRECTION_MAP
                .getOrDefault(name, name)
                .uppercase()

            """
            @JsValue("$name")
            val $memberName : $EVENT_TYPE<$eventType>
            """.trimIndent()
        }
}

private fun eventTypes(
    items: List<EventData>,
): ConversionResult {
    val firstItem = items.first()
    val typeName = firstItem.typeName

    val info = EVENT_INFO_MAP.getValue(typeName)
    val imports = if (info.name != "Event") "import ${info.fqn}" else ""

    val members = items
        .sortedBy { it.name }
        .map { (name, type) ->
            val memberName = EVENT_CORRECTION_MAP
                .getOrDefault(name, name)
                .uppercase()

            val finalType = when (type) {
                "MessageEvent",
                "ProgressEvent",
                -> "$type<*>"

                else -> type
            }

            """
            inline val $typeName.Companion.$memberName : $EVENT_TYPE<$finalType>
                get() = $EVENT_TYPE("$name")
            """.trimIndent()
        }

    val body = sequenceOf(imports)
        .filter { it.isNotEmpty() }
        .plus(members)
        .joinToString("\n\n")

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
