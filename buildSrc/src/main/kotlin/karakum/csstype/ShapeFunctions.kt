package karakum.csstype

internal fun shapeFunctions(): ConversionResult {
    val declarations = insetFunctions()

    return ConversionResult(
        name = "Shape.functions",
        body = declarations.joinToString("\n\n"),
    )
}

private val ROUND_PARAMETERS = listOf(
    "round" to "Round",
    "borderRadius" to LENGTH,
)

private fun insetFunctions(): Sequence<String> {
    val basicParameters = LAYOUT_PARAMETERS.drop(1)
        .map { it.map { it to LENGTH } }

    val roundParameters = LAYOUT_PARAMETERS
        .map { it.map { it to LENGTH } }
        .map { it + ROUND_PARAMETERS }

    return (basicParameters + roundParameters)
        .asSequence()
        .map { it.toTypedArray() }
        .map { parameters ->
            function(
                name = "inset",
                returnType = BASIC_SHAPE,
                parameters = parameters,
                delimiter = " ",
            )
        }
}
