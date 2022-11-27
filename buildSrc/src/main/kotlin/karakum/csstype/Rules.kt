package karakum.csstype

internal const val RULES = "Rules"

// language=Kotlin
private val BODY = """
import js.core.Record

typealias $RULES = Record<$SELECTOR, Any>
""".trimIndent()

internal fun Rules(): ConversionResult =
    ConversionResult(RULES, BODY)
