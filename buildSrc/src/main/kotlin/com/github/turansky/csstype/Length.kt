package com.github.turansky.csstype

private const val LENGTH = "Length"

internal fun Length(): ConversionResult {
    val declarations = sequenceOf(
        "sealed external interface $LENGTH: $LENGTH_TYPE",

        """
            inline fun $LENGTH(value: String): $LENGTH =
                value.unsafeCast<$LENGTH>()
        """.trimIndent()
    ) + LENGTH_UNITS.map { (name, suffix) ->
        """
            inline val Number.$name: $LENGTH
                get() = "${'$'}{this}$suffix".unsafeCast<$LENGTH>()
        """.trimIndent()
    }

    return ConversionResult(LENGTH, declarations.joinToString("\n\n"))
}
