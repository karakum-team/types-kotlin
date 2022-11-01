package karakum.browser

private val CLASSES = listOf(
    "Node",
    "Element",

    "NodeList",

    "DOMStringList",
    "DOMStringMap",
    "DOMImplementation",
    "DOMTokenList",

    "ScrollOptions",
    "ScrollToOptions",
    "ScrollIntoViewOptions",

    "Attr",
    "CDATASection",
    "Comment",
    "ElementCreationOptions",
    "NonElementParentNode",
    "ParentNode",
    "HTMLOrSVGScriptElement", // check
    "DocumentType",
    "NodeFilter",
    "TreeWalker",
    "Text",
    "ProcessingInstruction",
    "Range",
    "NodeIterator",
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

private val PARSING_CLASSES = listOf(
    "DOMParser",
)

internal fun domAliases(): List<ConversionResult> =
    (CLASSES + GEOMETRY_CLASSES + PARSING_CLASSES).map { name ->
        val aliasPkg = when (name) {
            in PARSING_CLASSES -> "org.w3c.dom.parsing"
            else -> "org.w3c.dom"
        }

        val alias = when (name) {
            "DOMStringList" -> "ReadonlyArray<String>"
            else -> "$aliasPkg.$name"
        }

        val pkg = when (name) {
            in GEOMETRY_CLASSES -> "dom.geometry"
            in PARSING_CLASSES -> "dom.parsing"
            else -> "dom"
        }

        ConversionResult(
            name = name,
            body = "typealias $name = $alias",
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
