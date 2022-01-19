package karakum.typescript

private val REQUIRED = setOf(
    "ApplicableRefactorInfo",
    "Bundle",
    "CodeAction",
    "CompletionEntryDataAutoImport",
    "CustomTransformers",
    "DocumentSpan",
    "EmitHelperBase",
    "GetCompletionsAtPositionOptions",
    "Identifier",
    "SourceFile",
    "SourceMapSpan",
    "TupleType",
)

internal fun convertMembers(
    name: String,
    source: String,
): String {
    if (source.isEmpty())
        return ""

    if (("(" in source || " & {" in source) && name !in REQUIRED)
        return "    /*\n" + source + "\n    */"

    return source.trimIndent()
        .removeSuffix(";")
        .splitToSequence(";\n")
        .map { convertMember(it) }
        .joinToString("\n")
}

private fun convertMember(
    source: String,
): String {
    if (source.startsWith("[") || source.startsWith("\" __sortedArrayBrand\""))
        return "    // $source"

    val comment = source.substringBeforeLast("\n", "")
        .ifEmpty { null }

    val body = source.substringAfterLast("\n")
    val content = convertProperty(body)

    return sequenceOf(comment, content)
        .filterNotNull()
        .joinToString("\n")
}

private fun convertProperty(
    source: String,
): String {
    var body = source.substringAfterLast("\n")
    val modifier = if (body.startsWith("readonly ")) "val" else "var"
    body = body.removePrefix("readonly ")

    val name = body.substringBefore(": ").removeSuffix("?")
    var type = kotlinType(body.substringAfter(": "), name)

    val optional = body.startsWith("${name}?")
    if (optional && type != DYNAMIC && !type.startsWith("$DYNAMIC ") && type != "Nothing?") {
        type = if (" /*" in type) type.replace(" /*", "? /*") else "$type?"
    }

    return "$modifier $name: $type"
}
