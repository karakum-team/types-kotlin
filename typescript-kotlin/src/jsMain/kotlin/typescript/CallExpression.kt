// Automatically generated - do not modify!

package typescript

external sealed interface CallExpression : LeftHandSideExpression, Declaration, Union.CallExpression_ {
override val kind: SyntaxKind.CallExpression
val expression: LeftHandSideExpression
val questionDotToken: QuestionDotToken?
val typeArguments: NodeArray<TypeNode>?
val arguments: NodeArray<Expression>
}
