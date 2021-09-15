package com.github.turansky.csstype

internal const val LINE_WIDTH_PROPERTY = "LineWidthProperty"

internal fun LineWidthProperty(): ConversionResult {
    val body = """
        // Globals | DataType.LineWidth
        sealed external interface $LINE_WIDTH_PROPERTY
    """.trimIndent()

    return ConversionResult(LINE_WIDTH_PROPERTY, body)
}
