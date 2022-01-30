// Automatically generated - do not modify!

package typescript

sealed external interface ModuleDeclaration : DeclarationStatement, JSDocContainer, Union.ModuleDeclaration_ {
    override val kind: SyntaxKind.ModuleDeclaration
    override val parent: dynamic /* ModuleBody | SourceFile */
    override val name: ModuleName
    val body: dynamic /* ModuleBody | JSDocNamespaceDeclaration */
}
