// Automatically generated - do not modify!

package typescript

external sealed interface SwitchStatement : Statement, FlowContainer, Union.SwitchStatement_ {
override val kind: SyntaxKind.SwitchStatement
val expression: Expression
val caseBlock: CaseBlock
var possiblyExhaustive: Boolean?
}
