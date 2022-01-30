// Automatically generated - do not modify!

package typescript

sealed external interface HeritageClause : Node, Union.HeritageClause {
    override val kind: SyntaxKind.HeritageClause
    override val parent: dynamic /* InterfaceDeclaration | ClassLikeDeclaration */
    val token: dynamic /* SyntaxKind.ExtendsKeyword | SyntaxKind.ImplementsKeyword */
    val types: NodeArray<ExpressionWithTypeArguments>
}
