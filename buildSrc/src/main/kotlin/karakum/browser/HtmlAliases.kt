package karakum.browser

internal val HTML_ALIAS_CLASSES = listOf(
    "HTMLElement",

    "HTMLCollection",
    "HTMLFormControlsCollection",
    "HTMLOptionsCollection",

    "Image",

    "Window",
    "WindowProxy",
    "WindowEventHandlers",

    // TEMP

    "HTMLCanvasElement",
    "HTMLIFrameElement",
    "HTMLMediaElement",
    "HTMLSlotElement",
)

internal fun htmlAliases(): List<ConversionResult> =
    HTML_ALIAS_CLASSES.map { name ->
        val alias = when (name) {
            "WindowProxy" -> "Window"
            else -> "org.w3c.dom.$name"
        }

        ConversionResult(
            name = name,
            body = "typealias $name = $alias",
            pkg = "dom.html",
        )
    }.plus(
        ConversionResult(
            name = "TextTrack",
            body = "sealed class TextTrack",
            pkg = "webvtt",
        )
    )
