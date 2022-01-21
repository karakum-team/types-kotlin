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
        "NamedDeclaration",
        "Node",
        -> content

        "CompletionEntryDataResolved",
        -> content
            .override("moduleSpecifier")

        "JsonSourceFile",
        -> content
            .override("statements")

        "ScopedEmitHelper",
        "UnscopedEmitHelper",
        -> content
            .override("scoped")
            .override("text")

        "TupleTypeReference",
        -> content
            .override("target")

        "SynthesizedComment",
        -> content
            .override("pos")
            .override("end")

        "FunctionOrConstructorTypeNodeBase",
        "IndexSignatureDeclaration",
        -> content
            .override("kind")
            .override("parent")
            .override("type")

        "PropertySignature",
        -> content
            .override("kind")
            .override("name")
            .override("questionToken")

        "JsonMinusNumericLiteral",
        -> content
            .override("kind")
            .override("operator")
            .override("operand")

        "ImportCall",
        "JsonObjectExpressionStatement",
        "JsxTagNamePropertyAccess",
        "PropertyAccessEntityNameExpression",
        "SuperCall",
        "SuperElementAccessExpression",
        "SuperPropertyAccessExpression",
        -> content
            .override("expression")
            .override("name")

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

        "UnparsedPrepend",
        "UnparsedPrologue",
        -> content
            .override("data")
            .override("kind")
            .override("parent")

        "ReadonlySet",
        -> content
            .override("has")

        "Set",
        -> content
            .override("delete")

        "Program",
        -> content
            .override("getCurrentDirectory")

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
        .replaceFirst("fun $name(", "override fun $name(")
        .replaceFirst("fun  $name(", "override fun $name(")
