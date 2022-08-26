// Automatically generated - do not modify!

package typescript

sealed external interface FunctionTypeNode : FunctionOrConstructorTypeNodeBase, Union.FunctionTypeNode_ {
    override val kind: SyntaxKind.FunctionType

    /** @deprecated A function type cannot have modifiers */
    override val modifiers: NodeArray<Modifier>?
}
