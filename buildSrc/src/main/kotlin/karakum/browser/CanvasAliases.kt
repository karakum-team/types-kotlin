package karakum.browser

private val CLASSES = listOf(
    "ImageBitmapRenderingContext",
    "ImageBitmapRenderingContextSettings",

    "ImageData",
    "Path2D",
)

internal fun canvasAliases(): List<ConversionResult> =
    CLASSES.map { name ->
        ConversionResult(
            name = name,
            body = "typealias $name = org.w3c.dom.$name",
            pkg = "canvas",
        )
    }
