package karakum.csstype

internal fun ReferenceFunctions(): ConversionResult {
    val declarations = sequenceOf(
        factory(
            name = "attr",
            returnType = "Content",
            parameters = arrayOf(
                "name" to "String",
            )
        ),
        factory(
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
