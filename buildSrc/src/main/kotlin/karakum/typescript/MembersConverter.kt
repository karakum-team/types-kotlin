package karakum.typescript

internal fun convertMembers(
    source: String,
): String {
    if (source.isEmpty())
        return ""

    if ("(" in source || " & {" in source)
        return "    /*\n" + source + "\n    */"

    return source.trimIndent()
        .removeSuffix(";")
        .splitToSequence(";\n")
        .map { convertMember(it) }
        .joinToString("\n")
}

internal fun convertMember(
    source: String,
): String {
    val comment = source.substringBeforeLast("\n", "")
        .ifEmpty { null }

    var body = source.substringAfterLast("\n")
    var modifier = if (body.startsWith("readonly ")) "val" else "var"
    body = body.removePrefix("readonly ")

    val name = body.substringBefore(": ").removeSuffix("?")
    var type = kotlinType(body.substringAfter(": "), name)

    val optional = body.startsWith("${name}?")
    if (optional) {
        type = if (" /*" in type) type.replace(" /*", "? /*") else "$type?"
    }

    when (name) {
        "kind",
        "name",
        "parent",
        -> modifier = "override $modifier"
    }

    return sequenceOf(
        comment,
        "$modifier $name: $type"
    ).filterNotNull()
        .joinToString("\n")
}
