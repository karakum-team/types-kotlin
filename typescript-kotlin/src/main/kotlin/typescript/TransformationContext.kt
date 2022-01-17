// Automatically generated - do not modify!

package typescript

external interface TransformationContext : CoreTransformationContext {
    /*
    /** Records a request for a non-scoped emit helper in the current context. */
    requestEmitHelper(helper: EmitHelper): void;
    /** Gets and resets the requested non-scoped emit helpers. */
    readEmitHelpers(): EmitHelper[] | undefined;
    /** Enables expression substitutions in the pretty printer for the provided SyntaxKind. */
    enableSubstitution(kind: SyntaxKind): void;
    /** Determines whether expression substitutions are enabled for the provided node. */
    isSubstitutionEnabled(node: Node): boolean;
    /**
     * Hook used by transformers to substitute expressions just before they
     * are emitted by the pretty printer.
     *
     * NOTE: Transformation hooks should only be modified during `Transformer` initialization,
     * before returning the `NodeTransformer` callback.
     */
    onSubstituteNode: (hint: EmitHint, node: Node) => Node;
    /**
     * Enables before/after emit notifications in the pretty printer for the provided
     * SyntaxKind.
     */
    enableEmitNotification(kind: SyntaxKind): void;
    /**
     * Determines whether before/after emit notifications should be raised in the pretty
     * printer when it emits a node.
     */
    isEmitNotificationEnabled(node: Node): boolean;
    /**
     * Hook used to allow transformers to capture state before or after
     * the printer emits a node.
     *
     * NOTE: Transformation hooks should only be modified during `Transformer` initialization,
     * before returning the `NodeTransformer` callback.
     */
    onEmitNode: (hint: EmitHint, node: Node, emitCallback: (hint: EmitHint, node: Node) => void) => void;
    */
}
