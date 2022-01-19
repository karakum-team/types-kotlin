package karakum.typescript

internal fun fixOverrides(
    name: String,
    content: String,
): String {
    if (name.endsWith("Predicate"))
        return content
            .override("kind")
            .override("type")

    return when (name) {
        "CommentRange",
        "DefinitionInfo",
        "ImplementationLocation",
        "JsxTagNamePropertyAccess",
        "NamedDeclaration",
        "Node",
        -> content

        "ImportCall",
        "SuperCall",
        -> content
            .override("expression")

        "AssertEntry",
        "JSDocLink",
        "JSDocLinkCode",
        "JSDocLinkPlain",
        "JSDocNameReference",
        "JSDocPropertyLikeTag",
        "JSDocSeeTag",
        "MetaProperty",
        "NamedTupleMember",
        -> content
            .override("kind")
            .override("parent")

        "ArrayDestructuringAssignment",
        "AssignmentExpression",
        "ObjectDestructuringAssignment",
        -> content
            .override("left")
            .override("operatorToken")

        "DiagnosticWithLocation",
        -> content
            .override("file")
            .override("start")
            .override("length")

        "BigIntLiteralType",
        "NumberLiteralType",
        "StringLiteralType",
        -> content
            .override("value")

        "ClassStaticBlockDeclaration",
        "FunctionLikeDeclarationBase",
        "ModuleDeclaration",
        -> content
            .override("kind")
            .override("name")
            .override("parent")

        else -> content
            .override("body")
            .override("kind")
            .override("name")
            .override("parent")
            .override("symbol")
    }
}

private fun String.override(
    name: String,
): String =
    replaceFirst("var $name:", "override var $name:")
        .replaceFirst("val $name:", "override val $name:")
