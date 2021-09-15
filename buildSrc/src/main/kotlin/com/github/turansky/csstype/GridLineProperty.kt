package com.github.turansky.csstype

internal const val GRID_LINE_PROPERTY = "GridLineProperty"

internal fun GridLineProperty(): ConversionResult {
    val body = """
        // Globals | DataType.GridLine
        sealed external interface $GRID_LINE_PROPERTY
    """.trimIndent()

    return ConversionResult(GRID_LINE_PROPERTY, body)
}
