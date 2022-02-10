package karakum.csstype

import karakum.common.kebabToCamel

internal const val FILTER = "Filter"

private const val NUMBER = "Number"
private const val STRING = "String"

private class FilterFactory(
    val name: String,
    val types: Array<out String>,
) {
    companion object {
        val FACTORIES = listOf(
            FilterFactory("blur", NUMBER, LENGTH),
            FilterFactory("brightness", NUMBER, PERCENT),
            FilterFactory("contrast", NUMBER, PERCENT),
            FilterFactory("drop-shadow", STRING),
            FilterFactory("grayscale", NUMBER, PERCENT),
            FilterFactory("hue-rotate", NUMBER, ANGLE),
            FilterFactory("invert", NUMBER, PERCENT),
            FilterFactory("opacity", NUMBER, PERCENT),
            FilterFactory("saturate", NUMBER, PERCENT),
            FilterFactory("sepia", NUMBER, PERCENT),
        )
    }
}

private fun FilterFactory(
    name: String,
    vararg types: String,
): FilterFactory =
    FilterFactory(name, types)

internal fun filterFactories(): String {
    val declarations = FilterFactory.FACTORIES
        .flatMap { f -> f.types.map { f.name to it } }
        .map { (name, type) ->
            """
            inline fun ${name.kebabToCamel()}(
                value: $type,
            ): $FILTER =
                "$name(${'$'}value)".unsafeCast<$FILTER>()
            """.trimIndent()
        }.plus(
            """
            fun $FILTER(
                vararg values: $FILTER,
            ): $FILTER =
                values.joinToString(" ")
                    .unsafeCast<$FILTER>()
            """.trimIndent()
        )

    return declarations.joinToString("\n\n")
}
