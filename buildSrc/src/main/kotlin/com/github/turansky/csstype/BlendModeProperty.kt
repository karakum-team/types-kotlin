package com.github.turansky.csstype

internal const val BLEND_MODE_PROPERTY = "BlendModeProperty"

internal fun BlendModeProperty(): ConversionResult {
    val body = """
        // Globals | DataType.BlendMode
        sealed external interface $BLEND_MODE_PROPERTY
    """.trimIndent()

    return ConversionResult(BLEND_MODE_PROPERTY, body)
}
