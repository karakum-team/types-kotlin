package karakum.csstype

internal const val CSS_DSL = "CssDsl"

private val CSS_DSL_BODY = """
import kotlinx.js.JsoDsl

typealias $CSS_DSL = JsoDsl
""".trimIndent()

internal fun CssDsl(): ConversionResult =
    ConversionResult(CSS_DSL, CSS_DSL_BODY)
