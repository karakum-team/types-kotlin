// Automatically generated - do not modify!

package typescript

/**
 * Unique identifier with a package name and version.
 * If changing this, remember to change `packageIdIsEqual`.
 */
external interface TransformationResult<T : Node> {
    /*
    /** Gets the transformed source files. */
    transformed: T[];
    /** Gets diagnostics for the transformation. */
    diagnostics?: DiagnosticWithLocation[];
    /**
     * Gets a substitute for a node, if one is available; otherwise, returns the original node.
     *
     * @param hint A hint as to the intended usage of the node.
     * @param node The node to substitute.
     */
    substituteNode(hint: EmitHint, node: Node): Node;
    /**
     * Emits a node with possible notification.
     *
     * @param hint A hint as to the intended usage of the node.
     * @param node The node to emit.
     * @param emitCallback A callback used to emit the node.
     */
    emitNodeWithNotification(hint: EmitHint, node: Node, emitCallback: (hint: EmitHint, node: Node) => void): void;
    /**
     * Indicates if a given node needs an emit notification
     *
     * @param node The node to emit.
     */
    isEmitNotificationEnabled?(node: Node): boolean;
    /**
     * Clean up EmitNode entries on any parse-tree nodes.
     */
    dispose(): void;
    */
}
