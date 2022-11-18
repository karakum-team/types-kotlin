package karakum.browser

internal val HTML_ALIAS_CLASSES = listOf(
    "Image",
)

internal fun htmlAliases(): List<ConversionResult> =
    HTML_ALIAS_CLASSES.map { name ->
        val alias = "org.w3c.dom.$name"

        ConversionResult(
            name = name,
            body = "typealias $name = $alias",
            pkg = "dom.html",
        )
    }
