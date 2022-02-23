package karakum.csstype

internal fun referenceFunctions(): ConversionResult {
    val declarations = sequenceOf(
        function(
            name = "attr",
            returnType = "Content",
            parameters = arrayOf(
                "name" to "String",
            )
        ),
        function(
            name = "env",
            returnType = "T",
            parameters = arrayOf(
                "id" to IDENT,
            )
        ),
        function(
            name = "env",
            returnType = "T",
            parameters = arrayOf(
                "id" to IDENT,
                "fallback" to "T",
            )
        ),
        function(
            name = "url",
            returnType = URL,
            parameters = arrayOf(
                "value" to "String",
            )
        ),
    )

    return ConversionResult(
        name = "Reference.functions",
        body = declarations.joinToString("\n\n"),
    )
}
