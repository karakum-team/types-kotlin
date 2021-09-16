package com.github.turansky.csstype

internal const val TIME_PROPERTY = "TimeProperty"

internal fun TimeProperty(): ConversionResult {
    val body = """
        // Globals | TTime
        sealed external interface $TIME_PROPERTY
    """.trimIndent()

    return ConversionResult(TIME_PROPERTY, body)
}
