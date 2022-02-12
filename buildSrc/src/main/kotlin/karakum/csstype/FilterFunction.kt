package karakum.csstype

private const val FILTER_FUNCTION = "FilterFunction"

private val PARENT_TYPES = listOf(
    "Filter",
    "BackdropFilter",
)

private const val NUMBER = "Number"

private class FilterFactory(
    val name: String,
    val types: Array<out String>,
) {
    companion object {
        val FACTORIES = listOf(
            FilterFactory("blur", NUMBER, LENGTH),
            FilterFactory("brightness", NUMBER, PERCENT),
            FilterFactory("contrast", NUMBER, PERCENT),
            FilterFactory("drop-shadow"),
            FilterFactory("grayscale", NUMBER, PERCENT),
            FilterFactory("hue-rotate", NUMBER, ANGLE),
            FilterFactory("invert", NUMBER, PERCENT),
            FilterFactory("opacity", NUMBER, PERCENT),
            FilterFactory("saturate", NUMBER, PERCENT),
            FilterFactory("sepia", NUMBER, PERCENT),
        )
    }
}

private val DROP_SHADOW_PARAMETERS = arrayOf(
    "offsetX" to LENGTH,
    "offsetY" to LENGTH,
    "blurRadius" to LENGTH,
    "color" to COLOR,
)

private fun FilterFactory(
    name: String,
    vararg types: String,
): FilterFactory =
    FilterFactory(name, types)

internal fun FilterFunction(): ConversionResult {
    val declarations = sequenceOf(
        """
        sealed external interface $FILTER_FUNCTION:
                ${PARENT_TYPES.joinToString(",\n")}
        """.trimIndent()
    ) + FilterFactory.FACTORIES
        .flatMap { f ->
            when (f.name) {
                "drop-shadow" -> (2..4)
                    .map { f.name to DROP_SHADOW_PARAMETERS.sliceArray(0..(it - 1)) }

                else -> f.types.map { f.name to arrayOf("value" to it) }
            }
        }
        .map { (name, parameters) -> factory(name, FILTER_FUNCTION, parameters, " ") }

    return ConversionResult(
        name = FILTER_FUNCTION,
        body = declarations.joinToString("\n\n"),
    )
}
