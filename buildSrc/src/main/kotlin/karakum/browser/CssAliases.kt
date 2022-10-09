package karakum.browser

internal val CSS_ALIAS_CLASSES = listOf(
    "ElementCSSInlineStyle",
    "LinkStyle",
)

internal fun cssAliases(): List<ConversionResult> =
    CSS_ALIAS_CLASSES.map { name ->
        ConversionResult(
            name = name,
            body = "typealias $name = org.w3c.dom.css.$name",
            pkg = "dom.css",
        )
    }
