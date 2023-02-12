package karakum.node

import karakum.common.ConversionResult
import karakum.common.unionBody

private val VALUES = listOf(
    "dir",
    "file",
    "junction",
)

// TODO: remove
internal fun SymlinkType(): ConversionResult =
    ConversionResult(
        "SymlinkType",
        unionBody("SymlinkType", VALUES)
    )
