package com.github.turansky.react

private val DOUBLE_NAMES = setOf(
    "width",
    "height",

    "min",
    "max",
    "step"
)

internal fun numberType(
    name: String,
): String {
    if (name in DOUBLE_NAMES)
        return DOUBLE

    return NUMBER
}
