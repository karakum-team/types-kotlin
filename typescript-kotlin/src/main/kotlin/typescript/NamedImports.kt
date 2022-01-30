// Automatically generated - do not modify!

package typescript

sealed external interface NamedImports : Node, Union.NamedImports {
    override val kind: SyntaxKind.NamedImports
    override val parent: ImportClause
    val elements: NodeArray<ImportSpecifier>
}
