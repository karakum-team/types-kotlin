package com.github.turansky.csstype

internal val LENGTH_UNITS = listOf(
    // Units
    LengthUnits("ch"),
    LengthUnits("em"),
    LengthUnits("ex"),
    LengthUnits("rem"),

    // Viewport-percentage lengths
    LengthUnits("vh"),
    LengthUnits("vw"),
    LengthUnits("vmin"),
    LengthUnits("vmax"),

    // Absolute length units
    LengthUnits("px"),
    LengthUnits("cm"),
    LengthUnits("mm"),
    LengthUnits("`in`", "in"),
    LengthUnits("pc"),
    LengthUnits("pt"),

    // Percent
    LengthUnits("pct", "%"),
)

internal data class LengthUnits(
    val name: String,
    val suffix: String,
) {
    constructor(name: String) : this(name, name)
}
