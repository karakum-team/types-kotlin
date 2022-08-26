// Automatically generated - do not modify!

package typescript

import kotlinx.js.ReadonlyArray

sealed external interface Node : ReadonlyTextRange {
    val kind: SyntaxKind
    val flags: NodeFlags
    val parent: Node
    fun getSourceFile(): SourceFile
    fun getChildCount(sourceFile: SourceFile = definedExternally): Int
    fun getChildAt(
        index: Int,
        sourceFile: SourceFile = definedExternally,
    ): Node

    fun getChildren(sourceFile: SourceFile = definedExternally): ReadonlyArray<Node>
    fun getStart(
        sourceFile: SourceFile = definedExternally,
        includeJsDocComment: Boolean = definedExternally,
    ): Int

    fun getFullStart(): Int
    fun getEnd(): Int
    fun getWidth(sourceFile: SourceFileLike = definedExternally): Int
    fun getFullWidth(): Int
    fun getLeadingTriviaWidth(sourceFile: SourceFile = definedExternally): Int
    fun getFullText(sourceFile: SourceFile = definedExternally): String
    fun getText(sourceFile: SourceFile = definedExternally): String
    fun getFirstToken(sourceFile: SourceFile = definedExternally): Node?
    fun getLastToken(sourceFile: SourceFile = definedExternally): Node?
    fun <T> forEachChild(
        cbNode: (node: Node) -> T?,
        cbNodeArray: (nodes: NodeArray<Node>) -> T? = definedExternally,
    ): T?

    /**
     * @deprecated `decorators` has been removed from `Node` and merged with `modifiers` on the `Node` subtypes that support them.
     * Use `ts.canHaveDecorators()` to test whether a `Node` can have decorators.
     * Use `ts.getDecorators()` to get the decorators of a `Node`.
     *
     * For example:
     * ```ts
     * const decorators = ts.canHaveDecorators(node) ? ts.getDecorators(node) : undefined;
     * ```
     */
    val decorators: Nothing?

    /**
     * @deprecated `modifiers` has been removed from `Node` and moved to the `Node` subtypes that support them.
     * Use `ts.canHaveModifiers()` to test whether a `Node` can have modifiers.
     * Use `ts.getModifiers()` to get the modifiers of a `Node`.
     *
     * For example:
     * ```ts
     * const modifiers = ts.canHaveModifiers(node) ? ts.getModifiers(node) : undefined;
     * ```
     */
    val modifiers: NodeArray<ModifierLike>?
}
