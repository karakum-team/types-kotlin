// Automatically generated - do not modify!

package typescript

sealed external interface VariableStatement : Statement, Union.VariableStatement_ {
    override val kind: SyntaxKind.VariableStatement
    override val modifiers: NodeArray<Modifier>?
    val declarationList: VariableDeclarationList
}
