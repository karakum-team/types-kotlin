// Automatically generated - do not modify!

package typescript

sealed external interface PropertyDeclaration : ClassElement, JSDocContainer, Union.PropertyDeclaration {
    override val kind: SyntaxKind.PropertyDeclaration
    override val parent: ClassLikeDeclaration
    override val name: PropertyName
    val questionToken: QuestionToken?
    val exclamationToken: ExclamationToken?
    val type: TypeNode?
    val initializer: Expression?
}
