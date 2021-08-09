package com.github.turansky.react

private val INT_NAMES = setOf(
    "span",
    "colSpan",
    "rowSpan",

    "maxLength",
    "minLength",
    "size",

    "rows",
    "cols",

    "twist",
    "charCode",
    "keyCode",
    "detail",

    "tabIndex",
    "start",

    "frameBorder",

    // Enums
    "eventPhase",
    "location",
    "which",
    "button",
    "buttons",
    "deltaMode",

    // SVG
    "numOctaves",
    "order",
)

private val DOUBLE_NAMES = setOf(
    "width",
    "height",

    "min",
    "max",
    "low",
    "high",
    "optimum",
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

    // SVG
    "alphabetic",
    "amplitude",
    "ascent",
    "azimuth",
    "baseFrequency",
    "bias",
    "by",
    "descent",
    "diffuseConstant",
    "divisor",
    "elevation",
    "exponent",
    "filterRes",
    "fontSize",
    "hanging",
    "ideographic",
    "intercept",

    "k",
    "k1",
    "k2",
    "k3",
    "k4",

    "limitingConeAngle",
    "mathematical",
    "opacity",
    "pathLength",
)

private val STRING_NAMES = setOf(
    "cellPadding",
    "cellSpacing",

    // SVG
    "baselineShift",
    "baseProfile",
    "bbox",
    "begin",
    "clip",
    "clipRule",
    "colorInterpolation",
    "colorProfile",
    "colorRendering",
    "contentScriptType",
    "contentStyleType",
    "cursor",
    "decelerate",
    "direction",
    "display",
    "dominantBaseline",
    "dur",
    "enableBackground",
    "end",
    "fillOpacity",
    "floodOpacity",
    "fontSizeAdjust",
    "fontStretch",
    "fontStyle",
    "fontVariant",
    "fontWeight",
    "format",
    "from",
    "g1",
    "g2",
    "glyphName",
    "glyphOrientationHorizontal",
    "glyphOrientationVertical",
    "glyphRef",
    "imageRendering",
    "in2",
    "kernelMatrix",
    "kernelUnitLength",
    "kerning",
    "keyPoints",
    "keySplines",
    "keyTimes",
    "lengthAdjust",
    "letterSpacing",
    "local",
    "mode",
    "offset",
    "operator",
    "orient",
    "orientation",
    "origin",
    "overflow",
    "paintOrder",
    "panose1",
    "patternTransform",
    "pointerEvents",
)

internal fun numberType(
    name: String,
): String =
    when {
        name in INT_NAMES -> INT
        name in DOUBLE_NAMES -> DOUBLE
        name in STRING_NAMES -> STRING

        name.endsWith("X") -> DOUBLE
        name.endsWith("Y") -> DOUBLE
        name.endsWith("Z") -> DOUBLE
        name.endsWith("Width") -> DOUBLE
        name.endsWith("Height") -> DOUBLE

        name.endsWith("Position") -> DOUBLE
        name.endsWith("Thickness") -> DOUBLE

        name.endsWith("Color") -> STRING
        name.endsWith("Mode") -> STRING
        name.endsWith("Units") -> STRING

        else -> {
            println(name)
            NUMBER
        }
    }
