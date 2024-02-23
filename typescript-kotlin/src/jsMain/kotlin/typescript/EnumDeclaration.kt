// Automatically generated - do not modify!

package typescript

external sealed interface EnumDeclaration : DeclarationStatement, JSDocContainer, Union.EnumDeclaration_ {
override val kind: SyntaxKind.EnumDeclaration
val modifiers: NodeArray<ModifierLike>?
override val name: Identifier
val members: NodeArray<EnumMember>
}
