package karakum.browser

internal val CONVERTED_WEBGL_TYPES = listOf(
    "WebGLContextEvent",
    "WebGLContextEventInit",
)

private val OLD_WEBGL_TYPES = listOf(
    "TexImageSource",
)

internal fun Aliases(): ConversionResult =
    ConversionResult(
        name = "Aliases",
        body = OLD_WEBGL_TYPES.joinToString("\n") { type ->
            "typealias $type = org.khronos.webgl.$type"
        },
    )
