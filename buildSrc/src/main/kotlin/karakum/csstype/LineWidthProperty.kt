package karakum.csstype

internal const val LINE_WIDTH_PROPERTY = "LineWidthProperty"

internal fun LineWidthProperty(): ConversionResult {
    val body = """
        sealed external interface $LINE_WIDTH_PROPERTY:
            $BORDER,
            $OUTLINE
    """.trimIndent()

    return ConversionResult(LINE_WIDTH_PROPERTY, body)
}
