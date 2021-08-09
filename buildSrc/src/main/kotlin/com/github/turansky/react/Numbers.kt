package com.github.turansky.react

private val INT_NAMES = setOf(
    "span",
    "colSpan",
    "rowSpan",

    "twist",
    "charCode",
    "keyCode",
    "detail",

    "tabIndex",

    // Enums
    "eventPhase",
    "location",
    "which",
    "button",
    "buttons",
    "deltaMode",
)

private val DOUBLE_NAMES = setOf(
    "width",
    "height",

    "min",
    "max",
    "step",

    "elapsedTime",
    "timeStamp",

    "pressure",
    "tangentialPressure",

    "cx", "cy",
    "dx", "dy",
    "fx", "fy",
    "rx", "ry",

    "x", "x1", "x2",
    "y", "y1", "y2",
    "z",

    "r", "radius",
)

internal fun numberType(
    name: String,
): String =
    when {
        name in INT_NAMES -> INT
        name in DOUBLE_NAMES -> DOUBLE

        name.endsWith("X") -> DOUBLE
        name.endsWith("Y") -> DOUBLE
        name.endsWith("Z") -> DOUBLE
        name.endsWith("Width") -> DOUBLE
        name.endsWith("Height") -> DOUBLE

        else -> {
            println(name)
            NUMBER
        }
    }
