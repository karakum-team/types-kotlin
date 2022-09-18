package karakum.csstype

import karakum.common.unionBody

internal const val ROUND = "Round"

internal fun Round(): ConversionResult =
    ConversionResult(
        name = ROUND,
        body = unionBody(ROUND, listOf("round")),
    )
