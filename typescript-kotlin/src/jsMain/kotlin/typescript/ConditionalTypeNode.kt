// Automatically generated - do not modify!

package typescript

external sealed interface ConditionalTypeNode : TypeNode, LocalsContainer {
override val kind: SyntaxKind.ConditionalType
val checkType: TypeNode
val extendsType: TypeNode
val trueType: TypeNode
val falseType: TypeNode
}
