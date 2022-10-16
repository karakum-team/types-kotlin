package karakum.browser

private val CLASSES = listOf(
    "CanvasCompositing",
    "CanvasDrawImage",
    "CanvasDrawPath",
    "CanvasFillStrokeStyles",
    "CanvasFilters",
    "CanvasGradient",
    "CanvasHitRegion",
    "CanvasImageData",
    "CanvasImageSmoothing",
    "CanvasImageSource",
    "CanvasPath",
    "CanvasPathDrawingStyles",
    "CanvasPattern",
    "CanvasRect",
    "CanvasRenderingContext2D",
    "CanvasRenderingContext2DSettings",
    "CanvasShadowStyles",
    "CanvasState",
    "CanvasText",
    "CanvasTextDrawingStyles",
    "CanvasTransform",
    "CanvasUserInterface",

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
