package karakum.node

import karakum.common.unionBody

private val VALUES = listOf(
    "ascii",
    "utf8",
    "utf16le",
    "ucs2",
    "base64",
    "base64url",
    "latin1",
    "binary",
    "hex",
)

// TODO: remove
internal fun BufferEncoding(): ConversionResult =
    ConversionResult(
        "BufferEncoding",
        unionBody("BufferEncoding", VALUES)
    )
