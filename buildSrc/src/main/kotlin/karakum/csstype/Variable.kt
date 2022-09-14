package karakum.csstype

internal const val VARIABLE = "Variable"

// language=Kotlin
private val BODY = """
sealed external interface $VARIABLE

inline fun $VARIABLE(
    name: String,
): $VARIABLE =
    name.unsafeCast<$VARIABLE>()

""".trimIndent()

internal fun Variable(): ConversionResult =
    ConversionResult(VARIABLE, BODY)
