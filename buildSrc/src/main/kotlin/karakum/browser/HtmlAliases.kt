package karakum.browser

internal val HTML_ALIAS_CLASSES = listOf(
    "HTMLElement",

    "HTMLCollection",
    "HTMLFormControlsCollection",
    "HTMLOptionsCollection",

    "Image",

    "WindowProxy",
    "WindowEventHandlers",

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
        val alias = when(name) {
            "WindowProxy" -> "org.w3c.dom.Window"
            else -> "org.w3c.dom.$name"
        }

        ConversionResult(
            name = name,
            body = "typealias $name = $alias",
            pkg = "dom.html",
        )
    }
