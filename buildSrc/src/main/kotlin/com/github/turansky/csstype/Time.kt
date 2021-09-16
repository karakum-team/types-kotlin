package com.github.turansky.csstype

private const val TIME = "Time"

internal fun Time(): ConversionResult {
    val body = "sealed external interface $TIME"
    return ConversionResult(TIME, body)
}
