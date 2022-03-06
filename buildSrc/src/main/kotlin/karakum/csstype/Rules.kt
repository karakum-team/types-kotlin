package karakum.csstype

internal const val RULES = "Rules"

// language=Kotlin
private val BODY = """
import kotlinx.js.Record

typealias $RULES = Record<$SELECTOR, Properties>
""".trimIndent()

internal fun Rules(): ConversionResult =
    ConversionResult(RULES, BODY)
