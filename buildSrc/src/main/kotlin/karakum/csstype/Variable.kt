package karakum.csstype

import karakum.common.ConversionResult

internal const val VARIABLE = "Variable"

// language=Kotlin
private val BODY = """
sealed external interface $VARIABLE<T : Any>

inline fun <T: Any> $VARIABLE(
    name: String,
): $VARIABLE<T> =
    name.unsafeCast<$VARIABLE<T>>()

""".trimIndent()

internal fun Variable(): ConversionResult =
    ConversionResult(VARIABLE, BODY)
