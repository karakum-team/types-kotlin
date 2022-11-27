package karakum.csstype

internal const val RULE_BUILDER = "RuleBuilder"

// language=Kotlin
private val BODY = """
import js.core.jso
import js.core.set
    
interface $RULE_BUILDER<T : Any> : $RULES {
    inline fun fontFace(
        block: FontFace.() -> Unit,
    ) {
        set($SELECTOR("@font-face"), jso(block))
    }

    inline operator fun $SELECTOR.invoke(
        block: T.() -> Unit,
    ) {
        set(this, jso(block))
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
    
    inline fun and(
        className: ClassName,
        block: T.() -> Unit,
    ) {
        $SELECTOR("&.${'$'}className")(block)
    }
}
""".trimIndent()

internal fun RuleBuilder(): ConversionResult =
    ConversionResult(RULE_BUILDER, BODY)
