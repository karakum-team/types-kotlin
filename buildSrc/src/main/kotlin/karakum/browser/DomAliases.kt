package karakum.browser

private val CLASSES = listOf(
    "Node",
    "Element",
    "Document",
    "DocumentFragment",

    "NodeList",

    "DOMPointInit",
    "DOMRectInit",
    "DOMRectList",
    "DOMStringMap",
    "DOMImplementation",
    "DOMTokenList",
    "DOMPointReadOnly",
    "DOMPoint",
    "DOMRect",
    "DOMRectReadOnly",
    "DOMQuad",
    "DOMMatrixReadOnly",
    "DOMMatrix",
    "DOMParser",

    "ScrollBehavior",
    "ScrollOptions",
    "ScrollToOptions",
)

internal fun domAliases(): List<ConversionResult> =
    CLASSES.map { name ->
        val pkg = when (name) {
            "DOMParser" -> "org.w3c.dom.parsing"
            else -> "org.w3c.dom"
        }

        ConversionResult(
            name = name,
            body = "typealias $name = $pkg.$name",
            pkg = "dom",
        )
    }
        .plus(
            ConversionResult(
                name = "NodeListOf",
                body = "typealias NodeListOf<T> = NodeList",
                pkg = "dom",
            )
        )
