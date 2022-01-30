// Automatically generated - do not modify!

package typescript

sealed external interface JsxFragment : PrimaryExpression, Union.JsxFragment {
    override val kind: SyntaxKind.JsxFragment
    val openingFragment: JsxOpeningFragment
    val children: NodeArray<JsxChild>
    val closingFragment: JsxClosingFragment
}
