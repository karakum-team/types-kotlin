package karakum.csstype

import karakum.common.unionBody

internal const val AUTO_REPEAT = "AutoRepeat"

internal fun AutoRepeat(): ConversionResult =
    ConversionResult(
        AUTO_REPEAT,
        unionBody(AUTO_REPEAT, listOf("auto-fill", "auto-fit")),
    )
