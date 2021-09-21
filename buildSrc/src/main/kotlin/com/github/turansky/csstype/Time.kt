package com.github.turansky.csstype

private const val TIME = "Time"

internal fun Time(): ConversionResult {
    val body = "sealed external interface $TIME: $TIME_PROPERTY"
    return ConversionResult(TIME, body)
}
