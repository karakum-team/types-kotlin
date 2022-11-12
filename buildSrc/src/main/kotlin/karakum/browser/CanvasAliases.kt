package karakum.browser

private val CLASSES = listOf(
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
