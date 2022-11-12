package karakum.browser

private val CLASSES = listOf(
    "DOMStringMap",
    "DOMTokenList",

    "HTMLOrSVGScriptElement", // check
    "ProcessingInstruction",
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
        val aliasPkg = "org.w3c.dom"

        val alias = when (name) {
            "DOMStringList" -> "ReadonlyArray<String>"
            else -> "$aliasPkg.$name"
        }

        val pkg = when (name) {
            in GEOMETRY_CLASSES -> "dom.geometry"
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
