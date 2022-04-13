package karakum.browser

private data class EventData(
    val name: String,
    val type: String,
) {
    val typeName: String = type.substringBefore("<")
}

internal fun eventDeclarations(): List<ConversionResult> =
    EVENT_SOURCES
        .splitToSequence("\n")
        .mapNotNull { parseEventData(it) }
        .distinct()
        .groupBy { it.typeName }
        // TODO: temp
        .filter { EVENT_TYPE_MAP.containsKey(it.key) }
        .map { (typeName, items) ->
            val imports = "import " + EVENT_TYPE_MAP.getValue(typeName)
            val members = items.map { (name, type) ->
                """
                    inline val $typeName.Companion.${name.toUpperCase()} : $EVENT_TYPE<$type>
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
        .plus(EventType())
        .plus(AnimationEvent())
        .plus(TransitionEvent())

private fun parseEventData(
    source: String,
): EventData? {
    if (!source.endsWith(";")) return null

    val (name, type) = source
        .removeSurrounding("    \"", ";")
        .split("\": ")

    return EventData(name, type)
}
