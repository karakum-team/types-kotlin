package karakum.browser

internal val OLD_WEBGL_TYPES = listOf(
    "TexImageSource",
    "WebGLActiveInfo",
    "WebGLBuffer",
    "WebGLContextAttributes",
    "WebGLContextEvent",
    "WebGLContextEventInit",
    "WebGLFramebuffer",
    "WebGLObject",
    "WebGLProgram",
    "WebGLRenderbuffer",
    "WebGLRenderingContext",
    "WebGLRenderingContextBase",
    "WebGLShader",
    "WebGLShaderPrecisionFormat",
    "WebGLTexture",
    "WebGLUniformLocation",
)

internal fun Aliases(): ConversionResult =
    ConversionResult(
        name = "Aliases",
        body = OLD_WEBGL_TYPES.joinToString("\n") { type ->
            "typealias $type = org.khronos.webgl.$type"
        },
    )
