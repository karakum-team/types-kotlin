package karakum.csstype

internal const val CSS_DSL = "CssDsl"

private val CSS_DSL_BODY = """
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.BINARY)
annotation class $CSS_DSL
""".trimIndent()

internal fun CssDsl(): ConversionResult =
    ConversionResult(CSS_DSL, CSS_DSL_BODY)
