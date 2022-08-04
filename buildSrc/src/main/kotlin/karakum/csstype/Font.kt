package karakum.csstype

internal const val FONT = "Font"

private val PARAMETERS = listOf(
    "style" to "FontStyle",
    "variant" to "FontVariant",
    "weight" to "FontWeight",
    "stretch" to "FontStretch",
    "size" to "FontSize",
    "lineHeight" to "LineHeight",
    "family" to "FontFamily",
)

internal fun fontFactories(): String =
    sequenceOf(
        PARAMETERS.toTypedArray(),
    ).joinToString("\n\n") { parameters ->
        factory(FONT, parameters)
    }
