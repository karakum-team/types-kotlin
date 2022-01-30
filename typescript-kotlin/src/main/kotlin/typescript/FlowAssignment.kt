// Automatically generated - do not modify!

package typescript

sealed external interface FlowAssignment : FlowNodeBase, Union.FlowAssignment_ {
    var node: dynamic /* Expression | VariableDeclaration | BindingElement */
    var antecedent: FlowNode
}
