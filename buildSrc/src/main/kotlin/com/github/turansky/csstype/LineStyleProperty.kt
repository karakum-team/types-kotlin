package com.github.turansky.csstype

internal const val LINE_STYLE_PROPERTY = "LineStyleProperty"

internal fun LineStyleProperty(): ConversionResult {
    val body = """
        sealed external interface $LINE_STYLE_PROPERTY: 
            $BORDER,
            $OUTLINE
    """.trimIndent()

    return ConversionResult(LINE_STYLE_PROPERTY, body)
}
