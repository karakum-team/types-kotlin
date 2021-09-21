package com.github.turansky.csstype

private const val LENGTH = "Length"

private val TYPES = listOf(
    // Units
    LengthType("ch"),
    LengthType("em"),
    LengthType("ex"),
    LengthType("rem"),

    // Viewport-percentage lengths
    LengthType("vh"),
    LengthType("vw"),
    LengthType("vmin"),
    LengthType("vmax"),

    // Absolute length units
    LengthType("px"),
    LengthType("cm"),
    LengthType("mm"),
    LengthType("`in`", "in"),
    LengthType("pc"),
    LengthType("pt"),

    // Percent
    LengthType("pct", "%"),
)

private data class LengthType(
    val name: String,
    val suffix: String,
) {
    constructor(name: String) : this(name, name)
}

internal fun Length(): ConversionResult {
    val declarations = sequenceOf(
        "sealed external interface $LENGTH: $LENGTH_TYPE",

        """
            inline fun $LENGTH(value: String): $LENGTH =
                value.unsafeCast<$LENGTH>()
        """.trimIndent()
    ) + TYPES.map { (name, suffix) ->
        """
            inline val Number.$name: $LENGTH
                get() = "${'$'}{this}$suffix".unsafeCast<$LENGTH>()
        """.trimIndent()
    }

    return ConversionResult(LENGTH, declarations.joinToString("\n\n"))
}
