package karakum.browser

internal val HTML_ALIAS_CLASSES = listOf(
    "HTMLElement",

    "HTMLCollection",
    "HTMLFormControlsCollection",
    "HTMLOptionsCollection",

    "Image",

    "MediaProvider",

    "Window",
    "WindowProxy",
    "WindowEventHandlers",
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
            body = "typealias TextTrack = org.w3c.dom.TextTrack",
            pkg = "webvtt",
        )
    )
        .plus(
            ConversionResult(
                name = "TextTrackList",
                body = "typealias TextTrackList = org.w3c.dom.TextTrackList",
                pkg = "webvtt",
            )
        )
