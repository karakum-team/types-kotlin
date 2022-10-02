package karakum.browser

import java.io.File

internal const val EVENT_TYPE = "EventType"

private data class EventData(
    val name: String,
    val type: String,
) {
    val typeName: String = type.substringBefore("<")
}

private val ADDITIONAL_EVENTS = listOf(
    EventData("webkitfullscreenchange", "Event"),
)

private val EXCLUDED = setOf(
    "AudioProcessingEvent",
    "MessageEvent",

    "MediaRecorderErrorEvent",
    "OfflineAudioCompletionEvent",
    "SpeechSynthesisErrorEvent",
    "SpeechSynthesisEvent",
)

internal fun eventDeclarations(
    definitionsFile: File,
): List<ConversionResult> =
    Regex("""interface .+?EventMap \{\n    "[\s\S]+?\n\}""")
        .findAll(definitionsFile.readText())
        .flatMap { it.value.splitToSequence("\n") }
        .mapNotNull { parseEventData(it) }
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

private fun parseEventData(
    source: String,
): EventData? {
    if (!source.endsWith(";")) return null

    val (name, type) = source
        .removeSurrounding("    \"", ";")
        .split("\": ", "<")

    return EventData(name, type)
}
