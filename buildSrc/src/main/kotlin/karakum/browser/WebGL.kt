package karakum.browser

import java.io.File

internal fun webglDeclarations(
    definitionsFile: File,
): Sequence<ConversionResult> =
    Regex("""interface ((EXT_|OES_|WEBGL_|WebGL).+?) \{[\s\S]+?\}""")
        .findAll(definitionsFile.readText())
        .map { it.value }
        .mapNotNull { convertInterface(it) }
        .plus(Aliases())
        .plus(Lists())
        .plus(Numbers())

private fun convertInterface(
    source: String,
): ConversionResult? {
    val name = source
        .substringAfter(" ")
        .substringBefore(" ")

    if (name in OLD_WEBGL_TYPES)
        return null

    val declaration = source.substringBefore(" {\n")
        .replace(" extends ", " : ")

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

    return convertFunction(source.replace(" | null", "?"))
}

private fun convertFunction(
    source: String,
): String {
    val name = source.substringBefore("(")
    val parameters = source
        .substringAfter("(")
        .substringBefore(")")
        .splitToSequence(", ")
        .filter { it.isNotEmpty() }
        .map {
            var (pname, ptype) = it.split(": ")
            ptype = when {
                ptype == "GLenum[]"
                -> "ReadonlyArray<GLenum>"

                ptype.startsWith("Int32Array | ")
                -> "Int32Array /* ${ptype.removePrefix("Int32Array")} */"

                else -> ptype
            }

            if (pname.endsWith("?")) {
                pname = pname.removeSuffix("?")
                ptype += "?"
            }

            "$pname: $ptype"
        }
        .toList()

    val params = if (parameters.size > 1) {
        parameters.joinToString(",\n", "\n", ",\n")
    } else parameters.joinToString("\n")

    val result = source.substringAfter(")")
        .removeSuffix(": void")
        .replace("string[]", "ReadonlyArray<String>")
        .replace("string", "String")

    return "fun $name($params)$result"
}
