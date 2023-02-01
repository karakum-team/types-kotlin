package karakum.actions

internal fun convert(
    content: String,
): Sequence<ConversionResult> {
    val body = cleanup(content)

    return ("\n" + body).splitToSequence("\nexport declare ", "\nexport ", "\n declare")
        .drop(1)
        .map { it.substringBefore("\n/**") }
        .mapNotNull { convertItem(it) }
}

private fun cleanup(
    content: String,
): String =
    content.splitToSequence("\n")
        .filter { line -> !line.startsWith("/// ") }
        .filter { line -> !line.startsWith("import ") }
        .filter { line -> !line.startsWith("    private _") }
        .joinToString("\n")
        .trim()

private fun convertItem(
    source: String,
): ConversionResult? {
    if (source.startsWith("{"))
        return null

    val name = source.substringAfter(" ")
        .substringBefore("<")
        .substringBefore(" ")
        .substringBefore("(")
        .substringBefore(":")

    return ConversionResult(
        name = name,
        body = source,
    )
}
