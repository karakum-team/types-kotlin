package karakum.browser

private val CLASSES = listOf(
    "DOMStringMap",
    "DOMTokenList",
)

internal fun domAliases(): List<ConversionResult> =
    listOf(
        ConversionResult(
            name = "NodeListOf",
            body = "typealias NodeListOf<T> = NodeList",
            pkg = "dom",
        )
    )
