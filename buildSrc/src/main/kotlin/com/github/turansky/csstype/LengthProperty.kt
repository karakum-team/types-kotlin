package com.github.turansky.csstype

internal const val LENGTH_PROPERTY = "LengthProperty"

internal fun LengthProperty(): ConversionResult {
    val body = """
        // Globals | TLength
        sealed external interface $LENGTH_PROPERTY
    """.trimIndent()

    return ConversionResult(LENGTH_PROPERTY, body)
}
