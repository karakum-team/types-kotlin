package karakum.router

internal fun convertMember(
    source: String,
): String {
    val comment = source.substringBeforeLast("\n", "")
    val body = source.substringAfterLast("\n")

    val declaration = if ("(" in body) {
        convertMethod(body)
    } else {
        convertParameter(body)
    }

    return sequenceOf(comment, declaration)
        .filter { it.isNotEmpty() }
        .joinToString("\n")
}

private fun convertMethod(
    source: String,
): String {
    return "fun " + source
        .replace("): () => void", "): () -> Unit")
        .replace("): void", ")")
        .replace("): string", "): String")
        .replace("delta: number", "delta: Int")
        .replace(", state?: any", ", state: Any = definedExternally")
}

private fun convertParameter(
    source: String,
): String {
    val name = source
        .substringBefore("?: ")
        .substringBefore(": ")
        .removePrefix("readonly ")

    var type = kotlinType(source.substringAfter(": "), name)
    if ("?: " in source && !type.endsWith("?"))
        type += "?"

    val modifier = if (source.startsWith("readonly ")) "val" else "var"
    return "$modifier $name: $type"
}
