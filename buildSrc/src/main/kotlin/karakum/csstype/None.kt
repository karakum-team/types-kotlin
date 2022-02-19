package karakum.csstype

import karakum.common.unionBody

internal const val NONE = "None"

internal fun None(): ConversionResult =
    ConversionResult(
        name = NONE,
        body = unionBody(NONE, listOf("none")),
    )
