// Automatically generated - do not modify!

package typescript

sealed external interface FunctionExpression : PrimaryExpression, FunctionLikeDeclarationBase, JSDocContainer,
    Union.FunctionExpression_ {
    override val kind: SyntaxKind.FunctionExpression
    override val modifiers: NodeArray<Modifier>?
    override val name: Identifier?
    override val body: FunctionBody
}
