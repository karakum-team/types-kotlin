// Automatically generated - do not modify!

package typescript

sealed external interface ArrayBindingPattern : Node, Union.ArrayBindingPattern {
    override val kind: SyntaxKind.ArrayBindingPattern
    override val parent: dynamic /* VariableDeclaration | ParameterDeclaration | BindingElement */
    val elements: NodeArray<ArrayBindingElement>
}
