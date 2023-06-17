package karakum.browser

internal object Mixins {
    private val SAFE: Set<String> = setOf(
        "LocaleOptions",
        "AbstractWorker",

        // DOM
        "ARIAMixin",
        "GlobalEventHandlers",
        "NonDocumentTypeChildNode",
        "Slottable",
    )

    val UNSAFE: Set<String> = setOf(
        "ElementCSSInlineStyle",
        "ChildNode",
        "HTMLOrSVGElement",
        "InnerHTML",
        "ParentNode",
        "ElementContentEditable",
    )

    val ALL: Set<String> = SAFE + UNSAFE
}
