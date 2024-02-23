// Automatically generated - do not modify!

package typescript

external sealed interface ParameterDeclaration : NamedDeclaration, JSDocContainer, Union.ParameterDeclaration_ {
override val kind: SyntaxKind.Parameter
override val parent: SignatureDeclaration
val modifiers: NodeArray<ModifierLike>?
val dotDotDotToken: DotDotDotToken?
override val name: BindingName
val questionToken: QuestionToken?
val type: TypeNode?
val initializer: Expression?
}
