// Automatically generated - do not modify!

package typescript

external interface NodesVisitor {
    fun <T : Node> /* native */ invoke(
        nodes: NodeArray<T>,
        visitor: Visitor?,
        test: (node: Node) -> Boolean = definedExternally,
        start: Double = definedExternally,
        count: Double = definedExternally,
    ): NodeArray<T>

    fun <T : Node> /* native */ invoke(
        nodes: NodeArray<T>?,
        visitor: Visitor?,
        test: (node: Node) -> Boolean = definedExternally,
        start: Double = definedExternally,
        count: Double = definedExternally,
    ): NodeArray<T>?
}
