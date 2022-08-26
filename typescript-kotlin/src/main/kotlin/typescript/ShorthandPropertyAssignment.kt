// Automatically generated - do not modify!

package typescript

sealed external interface ShorthandPropertyAssignment : ObjectLiteralElement, JSDocContainer,
    Union.ShorthandPropertyAssignment_ {
    override val kind: SyntaxKind.ShorthandPropertyAssignment
    override val parent: ObjectLiteralExpression
    override val name: Identifier
    val equalsToken: EqualsToken?
    val objectAssignmentInitializer: Expression?

    /** @deprecated A shorthand property assignment cannot have modifiers */
    val modifiers: NodeArray<Modifier>?


}
