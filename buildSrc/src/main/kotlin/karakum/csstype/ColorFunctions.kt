package karakum.csstype

internal fun colorFunctions(): ConversionResult {
    val declarations = sequenceOf(
        factory(
            COLOR,
            COLOR, // TODO: remove
            parameters = arrayOf(
                "value" to STRING,
            )
        ),
        function(
            name = "rgb",
            returnType = COLOR,
            parameters = arrayOf(
                "red" to "Int",
                "green" to "Int",
                "blue" to "Int",
            ),
        ),
        function(
            name = "rgba",
            returnType = COLOR,
            parameters = arrayOf(
                "red" to "Int",
                "green" to "Int",
                "blue" to "Int",
                "alpha" to "Double",
            ),
        ),
    )

    return ConversionResult(
        name = "Color.functions",
        body = declarations.joinToString("\n\n"),
    )
}
