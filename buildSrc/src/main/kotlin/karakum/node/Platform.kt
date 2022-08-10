package karakum.node

import karakum.common.unionBody

private val VALUES = listOf(
    "aix",
    "android",
    "darwin",
    "freebsd",
    "haiku",
    "linux",
    "openbsd",
    "sunos",
    "win32",
    "cygwin",
    "netbsd"
)

// TODO: remove
internal fun Platform(): ConversionResult =
    ConversionResult(
        "Platform",
        unionBody("Platform", VALUES)
    )
