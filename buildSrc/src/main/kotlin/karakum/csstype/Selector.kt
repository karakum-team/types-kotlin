package karakum.csstype

internal const val SELECTOR = "Selector"

// language=Kotlin
private val BODY = """
external interface $SELECTOR

inline fun $SELECTOR(
    syntax: String,
): $SELECTOR =
    syntax.unsafeCast<$SELECTOR>()

""".trimIndent()

internal fun Selector(): ConversionResult =
    ConversionResult(SELECTOR, BODY)
