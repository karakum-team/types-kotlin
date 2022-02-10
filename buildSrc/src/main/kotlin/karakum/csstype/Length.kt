package karakum.csstype

import karakum.common.sealedUnionBody

const val LENGTH = "Length"

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
        unitsExtension(LENGTH, name, suffix)
    }

    return ConversionResult(LENGTH, declarations.joinToString("\n\n"))
}
