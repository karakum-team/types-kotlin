package karakum.browser

private val ALIAS_MAP = listOf(
    "MediaError" to "dom.html",
)

internal fun mediaAliases(): List<ConversionResult> =
    ALIAS_MAP.map { (name, pkg) ->
        ConversionResult(
            name = name,
            body = "typealias $name = org.w3c.dom.$name",
            pkg = pkg,
        )
    }
