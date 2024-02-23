// Automatically generated - do not modify!

package typescript

external sealed interface CatchClause : Node, LocalsContainer, Union.CatchClause_ {
override val kind: SyntaxKind.CatchClause
override val parent: TryStatement
val variableDeclaration: VariableDeclaration?
val block: Block
}
