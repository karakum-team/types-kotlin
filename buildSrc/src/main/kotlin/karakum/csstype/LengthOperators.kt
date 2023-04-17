package karakum.csstype

import karakum.common.ConversionResult

internal fun lengthOperators(): ConversionResult {
    val operators = listOf(
        listOf("plus", "+", LENGTH, LENGTH),
        listOf("minus", "-", LENGTH, LENGTH),
        listOf("times", "*", "Number", LENGTH),
        listOf("div", "/", LENGTH, "Number")
    ).map { (name, operator, receiverType, parameterType) ->
        """
            inline operator fun $receiverType.$name(other: $parameterType): $LENGTH =
                "calc(${'$'}this $operator ${'$'}other)".unsafeCast<$LENGTH>()
        """.trimIndent()
    }

    val declarations = sequenceOf(
        """
            inline operator fun $LENGTH.unaryMinus(): $LENGTH =
                "-${'$'}this".unsafeCast<$LENGTH>()
        """.trimIndent()
    ) + operators

    return ConversionResult(
        name = "$LENGTH.operators",
        body = declarations.joinToString("\n\n"),
    )
}
