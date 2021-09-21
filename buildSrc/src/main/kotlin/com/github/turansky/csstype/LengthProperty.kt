package com.github.turansky.csstype

internal const val LENGTH_PROPERTY = "LengthProperty"

internal fun LengthProperty(
    parentProvider: ParentProvider,
): ConversionResult {
    val body = """
        sealed external interface $LENGTH_PROPERTY:
        $AUTO_LENGTH_PROPERTY,
        ${parentProvider.get()}
    """.trimIndent()

    return ConversionResult(LENGTH_PROPERTY, body)
}
