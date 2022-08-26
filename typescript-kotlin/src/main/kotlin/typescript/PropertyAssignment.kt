// Automatically generated - do not modify!

package typescript

sealed external interface PropertyAssignment : ObjectLiteralElement, JSDocContainer, Union.PropertyAssignment_ {
    override val kind: SyntaxKind.PropertyAssignment
    override val parent: ObjectLiteralExpression
    override val name: PropertyName
    val initializer: Expression

    /** @deprecated A property assignment cannot have a question token */
    val questionToken: QuestionToken?

    /** @deprecated A property assignment cannot have an exclamation token */
    val exclamationToken: ExclamationToken?
}
