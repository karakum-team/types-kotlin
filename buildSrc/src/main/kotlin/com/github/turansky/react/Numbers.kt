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
): String =
    when {
        name in DOUBLE_NAMES -> DOUBLE

        name.endsWith("X") -> DOUBLE
        name.endsWith("Y") -> DOUBLE
        name.endsWith("Z") -> DOUBLE
        name.endsWith("Width") -> DOUBLE
        name.endsWith("Height") -> DOUBLE

        else -> NUMBER
    }
