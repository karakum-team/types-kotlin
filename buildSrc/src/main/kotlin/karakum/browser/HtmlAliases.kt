package karakum.browser

internal val HTML_ALIAS_CLASSES = listOf(
    "HTMLElement",

    "HTMLCollection",
    "HTMLFormControlsCollection",
    "HTMLOptionsCollection",

    "Image",

    // TEMP

    "HTMLCanvasElement",
    "HTMLIFrameElement",
    "HTMLInputElement",
    "HTMLMediaElement",
    "HTMLSlotElement",
    "HTMLVideoElement",
)

internal fun htmlAliases(): List<ConversionResult> =
    HTML_ALIAS_CLASSES.map { name ->
        ConversionResult(
            name = name,
            body = "typealias $name = org.w3c.dom.$name",
            pkg = "dom.html",
        )
    }
