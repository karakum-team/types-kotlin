// Automatically generated - do not modify!

package typescript

sealed external interface TypePredicateNode : TypeNode, Union.TypePredicateNode {
    override val kind: SyntaxKind.TypePredicate
    override val parent: dynamic /* SignatureDeclaration | JSDocTypeExpression */
    val assertsModifier: AssertsKeyword?
    val parameterName: dynamic /* Identifier | ThisTypeNode */
    val type: TypeNode?
}
