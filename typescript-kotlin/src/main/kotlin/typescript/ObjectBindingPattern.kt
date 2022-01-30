// Automatically generated - do not modify!

package typescript

sealed external interface ObjectBindingPattern : Node, Union.ObjectBindingPattern_ {
    override val kind: SyntaxKind.ObjectBindingPattern
    override val parent: dynamic /* VariableDeclaration | ParameterDeclaration | BindingElement */
    val elements: NodeArray<BindingElement>
}
