package com.github.turansky.csstype

import com.github.turansky.common.sealedUnionBody

private const val LENGTH = "Length"

private val LENGTH_LIKE_VALUES = listOf(
    "auto",
    "fit-content",
    "intrinsic",
    "max-content",
    "min-content",
    "min-intrinsic",
    "none",
    "normal",
)


internal fun Length(): ConversionResult {
    val declarations = sequenceOf(
        sealedUnionBody(LENGTH, LENGTH_TYPE, LENGTH_LIKE_VALUES),

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
