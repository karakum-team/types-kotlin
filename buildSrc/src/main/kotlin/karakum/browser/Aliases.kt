package karakum.browser

internal val OLD_WEBGL_TYPES = listOf(
    "TexImageSource",
    "WebGLContextAttributes",
    "WebGLContextEvent",
    "WebGLContextEventInit",
    "WebGLRenderingContextBase",
)

internal fun Aliases(): ConversionResult =
    ConversionResult(
        name = "Aliases",
        body = OLD_WEBGL_TYPES.joinToString("\n") { type ->
            "typealias $type = org.khronos.webgl.$type"
        },
    )
