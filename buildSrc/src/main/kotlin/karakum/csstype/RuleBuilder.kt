package karakum.csstype

internal const val RULE_BUILDER = "RuleBuilder"

// language=Kotlin
private val BODY = """
import kotlinx.js.jso    
    
interface $RULE_BUILDER<T : Any> {
    inline operator fun $SELECTOR.invoke(
        block: T.() -> Unit,
    ) {
        this@$RULE_BUILDER.unsafeCast<$RULES>()[this] = jso(block)
    }

    inline operator fun $CLASS_NAME.invoke(
        block: T.() -> Unit,
    ) {
        $SELECTOR(".${'$'}this")(block)
    }

    inline operator fun String.invoke(
        block: T.() -> Unit,
    ) {
        $SELECTOR(this)(block)
    }
}
""".trimIndent()

internal fun RuleBuilder(): ConversionResult =
    ConversionResult(RULE_BUILDER, BODY)
