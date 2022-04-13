package karakum.browser

private data class EventData(
    val name: String,
    val type: String,
)

internal fun eventDeclarations(): List<ConversionResult> =
    EVENT_SOURCES
        .splitToSequence("\n")
        .mapNotNull { parseEventData(it) }
        .distinct()
        .groupBy { it.type }
        // TODO: temp
        .filter { EVENT_TYPE_MAP.containsKey(it.key) }
        .map { (type, items) ->
            val imports = "import " + EVENT_TYPE_MAP.getValue(type)
            val members = items.map { (name) ->
                """
                    inline val $type.Companion.${name.toUpperCase()} : $EVENT_TYPE<$type>
                        get() = $EVENT_TYPE("$name")                        
                """.trimIndent()
            }

            val body = sequenceOf(imports)
                .plus(members)
                .joinToString("\n\n")

            ConversionResult(
                name = "$type.types",
                body = body,
            )
        }
        .plus(eventType())

private fun parseEventData(
    source: String,
): EventData? {
    if (!source.endsWith(";")) return null

    val (name, type) = source
        .removeSurrounding("    \"", ";")
        .split("\": ")

    return EventData(name, type)
}
