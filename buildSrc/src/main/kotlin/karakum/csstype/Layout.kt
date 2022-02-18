package karakum.csstype

internal val LAYOUT_CLASSES = setOf(
    "Inset",
    "Margin",
    "Padding",
)

private val PARAMETERS = listOf(
    listOf("vertical", "horizontal"),
    listOf("top", "horizontal", "bottom"),
    listOf("top", "right", "bottom", "left"),
)

internal fun layoutFactories(name: String): String {
    val parameterType = when (name) {
        "Padding" -> LENGTH
        // TODO: use AutoLength
        else -> AUTO_LENGTH_PROPERTY
    }

    return PARAMETERS.asSequence()
        .map { names -> names.map { it to parameterType } }
        .map { factory(name, it.toTypedArray()) }
        .joinToString("\n\n")
}
