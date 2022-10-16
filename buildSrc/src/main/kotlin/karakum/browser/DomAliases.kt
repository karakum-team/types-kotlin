package karakum.browser

private val CLASSES = listOf(
    "Node",
    "Element",
    "Document",
    "DocumentFragment",

    "NodeList",

    "DOMStringMap",
    "DOMImplementation",
    "DOMTokenList",
    "DOMParser",

    "ScrollOptions",
    "ScrollToOptions",
    "ScrollIntoViewOptions",
)

private val GEOMETRY_CLASSES = listOf(
    "DOMPointReadOnly",
    "DOMPoint",
    "DOMPointInit",

    "DOMRectReadOnly",
    "DOMRect",
    "DOMRectInit",
    "DOMRectList",

    "DOMMatrixReadOnly",
    "DOMMatrix",

    "DOMQuad",
)


internal fun domAliases(): List<ConversionResult> =
    (CLASSES + GEOMETRY_CLASSES).map { name ->
        val aliasPkg = when (name) {
            "DOMParser" -> "org.w3c.dom.parsing"
            else -> "org.w3c.dom"
        }

        val pkg = if (name in GEOMETRY_CLASSES) "dom.geometry" else "dom"

        ConversionResult(
            name = name,
            body = "typealias $name = $aliasPkg.$name",
            pkg = pkg,
        )
    }
        .plus(
            ConversionResult(
                name = "NodeListOf",
                body = "typealias NodeListOf<T> = NodeList",
                pkg = "dom",
            )
        )
