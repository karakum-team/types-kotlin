package karakum.browser

private val CLASSES = listOf(
    "DOMStringMap",
    "DOMTokenList",
)

internal fun domAliases(): List<ConversionResult> =
    CLASSES.map { name ->
        val aliasPkg = "org.w3c.dom"

        val alias = when (name) {
            "DOMStringList" -> "ReadonlyArray<String>"
            else -> "$aliasPkg.$name"
        }

        ConversionResult(
            name = name,
            body = "typealias $name = $alias",
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
