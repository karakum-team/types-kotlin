package com.github.turansky.csstype

internal const val TIME_PROPERTY = "TimeProperty"

internal fun TimeProperty(
    parentProvider: ParentProvider,
): ConversionResult {
    val body = """
        // Globals | TTime
        sealed external interface $TIME_PROPERTY:
        ${parentProvider.get()}
    """.trimIndent()

    return ConversionResult(TIME_PROPERTY, body)
}
