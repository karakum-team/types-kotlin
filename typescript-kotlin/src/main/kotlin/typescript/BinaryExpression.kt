// Automatically generated - do not modify!

package typescript

sealed external interface BinaryExpression : Expression, Declaration, Union.BinaryExpression {
    override val kind: SyntaxKind.BinaryExpression
    val left: Expression
    val operatorToken: BinaryOperatorToken
    val right: Expression
}
