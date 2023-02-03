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

    if (source.startsWith("default "))
        return null

    val type = source.substringBefore(" ")
    if (type == "interface") {
        return convertInterface(
            source = source.substringAfter(" ")
        )
    }

    val name = source.substringAfter(" ")
        .substringBefore("<")
        .substringBefore(" ")
        .substringBefore("(")
        .substringBefore(":")

    var body = source
    if (body.startsWith("function ") && "():" !in body)
        body = body
            .replaceFirst("(", "(\n")
            .replace(", ", ",\n")
            .replaceFirst("):", ",\n):")

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")

    val declaration = source.substringBefore(" {\n")

    val memberSource = source.substringAfter(" {\n")
        .substringBefore("\n}")
        .replace("/**`", "/ **`")
        .replace("/*`", "/ *`")
        .trimIndent()

    if (memberSource == "[key: string]: any;")
        return ConversionResult(
            name = name,
            body = "typealias $name = Record<String, Any>"
        )

    var members = memberSource
        .replace("env?: {\n    [key: string]: string;\n};", "env?: Record<string, string>;")
        .splitToSequence("\n")
        .joinToString("\n") { line ->
            if (line.endsWith(";")) {
                convertMember(line.removeSuffix(";"))
            } else {
                line
            }
        }
        .prependIndent("    ")

    val body = "external interface $declaration {\n$members\n}"

    return ConversionResult(
        name = name,
        body = body,
    )
}

private fun convertMember(
    source: String,
): String =
    if ("(" in source.substringBefore(":")) {
        convertMethod(source)
    } else {
        convertProperty(source)
    }

private fun convertProperty(
    source: String,
): String {
    val nameSource = source.substringBefore(": ")
    val typeSource = source.substringAfter(": ")

    val name = nameSource.removeSuffix("?")
    var type = when (typeSource) {
        "boolean" -> "Boolean"
        "string" -> "String"
        "number" -> "Number"

        "string[]" -> "ReadonlyArray<String>"
        "Record<string, string>" -> "Record<String, String>"

        "typeof http | typeof https" -> "Any /* $typeSource */"

        else -> when {
            "." in typeSource -> "node.$typeSource"
            typeSource.endsWith("[]") -> {
                "ReadonlyArray<${typeSource.removeSuffix("[]")}>"
            }

            else -> {
                typeSource
                    .replace(": string)", ": String)")
                    .replace(") => void", ") -> Unit")
                    .replace(" | null", "?")
            }
        }
    }

    if (nameSource.endsWith("?")) {
        if (type.startsWith("("))
            type = "($type)"

        type += "?"
    }

    return "var $name: $type"
}

private fun convertMethod(
    source: String,
): String {
    return "// $source"
}
