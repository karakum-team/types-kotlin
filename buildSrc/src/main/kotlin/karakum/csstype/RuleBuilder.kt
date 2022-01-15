package karakum.csstype

internal const val RULE_BUILDER = "RuleBuilder"

// language=Kotlin
private val BODY = """
interface $RULE_BUILDER<T: Any> {
    inline operator fun String.invoke(
        block: T.() -> Unit,
    ) {
        this@$RULE_BUILDER.asDynamic()[this] = js("({})").unsafeCast<T>().apply(block)
    }
}
""".trimIndent()

internal fun RuleBuilder(): ConversionResult =
    ConversionResult(RULE_BUILDER, BODY)
