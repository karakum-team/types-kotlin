package karakum.browser

import java.io.File

internal fun webglDeclarations(
    definitionsFile: File,
): Sequence<ConversionResult> =
    Regex("""interface (EXT|OES|WEBGL)_.+? \{[\s\S]+?\}""")
        .findAll(definitionsFile.readText())
        .map { it.value }
        .map { convertInterface(it) }
        .plus(Aliases())
        .plus(Numbers())

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source
        .substringAfter(" ")
        .substringBefore(" ")

    val declaration = source.substringBefore(" {\n")

    val memberSource = source
        .substringAfter(" {\n")
        .removeSuffix("}")
        .removeSuffix(";\n")
        .trimIndent()

    val members = if (memberSource.isNotEmpty()) {
        memberSource
            .splitToSequence(";\n")
            .joinToString("\n") { convertMember(it) }
    } else ""

    val body = "sealed external $declaration {\n$members\n}"

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertMember(
    source: String,
): String {
    if ("(" !in source)
        return source.replace("readonly ", "val ")

    return "fun " + source
        .replace(" | null", "?")
}
