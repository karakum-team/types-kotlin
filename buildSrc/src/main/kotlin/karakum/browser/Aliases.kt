package karakum.browser

private val TYPES = listOf(
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
        body = TYPES.joinToString("\n") { type ->
            "typealias $type = org.khronos.webgl.$type"
        },
    )
