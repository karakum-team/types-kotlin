package karakum.csstype

import karakum.common.unionBody

internal const val AT = "At"

internal fun At(): ConversionResult =
    ConversionResult(
        name = AT,
        body = unionBody(AT, listOf("at")),
    )
