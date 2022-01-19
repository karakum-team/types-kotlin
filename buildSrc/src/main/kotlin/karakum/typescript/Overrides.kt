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
        "ImportCall",
        "JsxTagNamePropertyAccess",
        "NamedDeclaration",
        "Node",
        -> content

        "ArrayDestructuringAssignment",
        -> content
            .override("left")

        "DiagnosticWithLocation",
        -> content
            .override("file")
            .override("start")
            .override("length")

        "NumberLiteralType",
        -> content
            .override("value")

        else ->
            content
                .override("kind")
                .override("name")
                .override("parent")
    }
}

private fun String.override(
    name: String,
): String =
    replaceFirst("var $name:", "override var $name:")
        .replaceFirst("val $name:", "override val $name:")
