// Automatically generated - do not modify!

package typescript

sealed external interface ForStatement : IterationStatement, Union.ForStatement {
    override val kind: SyntaxKind.ForStatement
    val initializer: ForInitializer?
    val condition: Expression?
    val incrementor: Expression?
}
