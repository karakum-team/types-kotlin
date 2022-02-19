package karakum.csstype

import karakum.common.unionBody

internal const val NONE = "None"

internal fun None(): ConversionResult {
    return ConversionResult(NONE, unionBody(NONE, listOf("none")))
}
