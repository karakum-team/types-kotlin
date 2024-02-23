// Automatically generated - do not modify!

package typescript

external sealed interface ClassDeclaration : ClassLikeDeclarationBase, DeclarationStatement, Union.ClassDeclaration_ {
override val kind: SyntaxKind.ClassDeclaration
val modifiers: NodeArray<ModifierLike>?
/** May be undefined in `export default class { ... }`. */
override val name: Identifier?
}
