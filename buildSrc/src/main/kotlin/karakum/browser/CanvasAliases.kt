package karakum.browser

private val CLASSES = listOf(
    "CanvasCompositing",
    "CanvasDirection",
    "CanvasDrawImage",
    "CanvasDrawPath",
    "CanvasFillRule",
    "CanvasFillStrokeStyles",
    "CanvasFilters",
    "CanvasGradient",
    "CanvasHitRegion",
    "CanvasImageData",
    "CanvasImageSmoothing",
    "CanvasImageSource",
    "CanvasLineCap",
    "CanvasLineJoin",
    "CanvasPath",
    "CanvasPathDrawingStyles",
    "CanvasPattern",
    "CanvasRect",
    "CanvasRenderingContext2D",
    "CanvasRenderingContext2DSettings",
    "CanvasShadowStyles",
    "CanvasState",
    "CanvasText",
    "CanvasTextAlign",
    "CanvasTextBaseline",
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
