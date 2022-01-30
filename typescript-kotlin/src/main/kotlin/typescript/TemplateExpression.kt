// Automatically generated - do not modify!

package typescript

sealed external interface TemplateExpression : PrimaryExpression, Union.TemplateExpression {
    override val kind: SyntaxKind.TemplateExpression
    val head: TemplateHead
    val templateSpans: NodeArray<TemplateSpan>
}
