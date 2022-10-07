package karakum.browser

import java.io.File

internal const val EVENT_TYPE = "EventType"

private val PACKAGE_MAP = mapOf(
    "AbortSignal" to "browser.events", // Common event?
    "Animation" to "animation",
    "AudioScheduledSourceNode" to "",
    "AudioWorkletNode" to "",
    "BaseAudioContext" to "",
    "Document" to "dom.events",
    "Element" to "dom.events",
    "EventSource" to "",
    "FontFaceSet" to "dom.events",
    "GlobalEventHandlers" to "dom.events",
    "HTMLMediaElement" to "dom.events",
    "HTMLVideoElement" to "dom.events",
    "HTMLVideoElement" to "dom.events",
    "IDBDatabase" to "idb",
    "IDBOpenDBRequest" to "idb",
    "IDBRequest" to "idb",
    "IDBTransaction" to "idb",
    "MediaDevices" to "",
    "MediaKeySession" to "",
    "MediaRecorder" to "",
    "MediaSource" to "",
    "MediaStreamTrack" to "",
    "Notification" to "",
    "PaymentRequest" to "",
    "Performance" to "",
    "PermissionStatus" to "",
    "PictureInPictureWindow" to "browser.events",
    "RTCDataChannel" to "webrtc",
    "RTCDtlsTransport" to "webrtc",
    "RTCPeerConnection" to "webrtc",
    "RTCSctpTransport" to "webrtc",
    "RemotePlayback" to "",
    "ScreenOrientation" to "browser.events",
    "ServiceWorker" to "worker",
    "ServiceWorkerContainer" to "worker",
    "ServiceWorkerRegistration" to "worker",
    "ShadowRoot" to "dom.events",
    "SourceBuffer" to "",
    "SourceBufferList" to "",
    "SpeechSynthesis" to "",
    "TextTrack" to "",
    "TextTrackCue" to "",
    "TextTrackList" to "",
    "VisualViewport" to "",
    "WebSocket" to "websocket",
    "Window" to "browser.events",
    "WindowEventHandlers" to "browser.events",
    "XMLHttpRequest" to "xhr",
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
        pkg = "Element", // ???
    ),
)

private val EXCLUDED = setOf(
    "MessageEvent",

    // deprecated
    "AudioProcessingEvent",
    "MediaRecorderErrorEvent",
    "SecurityPolicyViolationEvent",

    // to use
    "OfflineAudioCompletionEvent",
    "SpeechSynthesisErrorEvent",
    "SpeechSynthesisEvent",
)

internal fun eventDeclarations(
    definitionsFile: File,
): List<ConversionResult> =
    Regex("""interface .+?EventMap \{\n    "[\s\S]+?\n\}""")
        .findAll(definitionsFile.readText())
        .flatMap { parseEvents(it.value) }
        .plus(ADDITIONAL_EVENTS)
        .distinct()
        .groupBy { it.typeName }
        .filter { it.key !in EXCLUDED }
        .map { (typeName, items) ->
            val imports = if (EVENT_TYPE_MAP.containsKey(typeName)) {
                "import " + EVENT_TYPE_MAP.getValue(typeName)
            } else {
                "import org.w3c.dom.events.Event as $typeName"
            }

            val members = items.map { (name, type) ->
                val memberName = EVENT_CORRECTION_MAP
                    .getOrDefault(name, name)
                    .toUpperCase()

                """
                    inline val $typeName.Companion.$memberName : $EVENT_TYPE<$type>
                        get() = $EVENT_TYPE("$name")                        
                """.trimIndent()
            }

            val body = sequenceOf(imports)
                .plus(members)
                .joinToString("\n\n")

            ConversionResult(
                name = "$typeName.types",
                body = body,
            )
        }
        .plus(AnimationEvent())
        .plus(TransitionEvent())

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
            .ifEmpty { "org.w3c.dom.events" }
    } else ""

    return EventData(
        name = name,
        type = type,
        pkg = pkg,
    )
}
