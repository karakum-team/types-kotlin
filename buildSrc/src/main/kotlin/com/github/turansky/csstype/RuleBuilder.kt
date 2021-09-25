package com.github.turansky.csstype

internal const val RULE_BUILDER = "RuleBuilder"

// language=Kotlin
private val BODY = """
interface $RULE_BUILDER<T: Any> {
    inline operator fun String.invoke(
        block: T.() -> Unit,
    ) {
        this@$RULE_BUILDER.asDynamic()[this] = block(js("({})"))
    }
}
""".trimIndent()

internal fun RuleBuilder(): ConversionResult =
    ConversionResult(RULE_BUILDER, BODY)
