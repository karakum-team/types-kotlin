// Automatically generated - do not modify!

package typescript

external sealed interface TryStatement : Statement, FlowContainer, Union.TryStatement_ {
override val kind: SyntaxKind.TryStatement
val tryBlock: Block
val catchClause: CatchClause?
val finallyBlock: Block?
}
