package karakum.csstype

import karakum.common.sealedUnionBody

internal const val NONE = "None"

internal fun None(): ConversionResult =
    ConversionResult(
        name = NONE,
        body = sealedUnionBody(NONE, listOf("none")),
    )
