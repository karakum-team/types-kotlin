package karakum.csstype

internal const val CLASS_NAME = "ClassName"

private val BODY = """
sealed external interface ClassName

inline fun $CLASS_NAME(
    value: String,
): $CLASS_NAME =
    value.unsafeCast<$CLASS_NAME>()
""".trimIndent()

internal fun ClassName(): ConversionResult =
    ConversionResult(CLASS_NAME, BODY)
