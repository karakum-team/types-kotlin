package karakum.csstype

import karakum.common.unionBody

internal const val AUTO = "Auto"

internal fun Auto(): ConversionResult =
    ConversionResult(
        name = AUTO,
        body = unionBody(AUTO, listOf("auto")),
    )
