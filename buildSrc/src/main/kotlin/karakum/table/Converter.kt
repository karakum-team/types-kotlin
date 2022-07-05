package karakum.table

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> =
    definitionFile.readText()
        .splitToSequence("\ndeclare ")
        .drop(1)
        .map { it.removeSuffix(";") }
        .map { convertDefinition(it) }

private fun convertDefinition(
    source: String,
): ConversionResult {
    val type = source.substringBefore(" ")
    val body = source.substringAfter(" ")

    return when (type) {
        "const" -> convertConst(body)
        "function" -> convertFunction(body)
        "type" -> convertType(body)
        else -> TODO()
    }
}

private fun convertConst(
    source: String,
): ConversionResult {
    val name = source.substringBefore(":")
    val type = if (": {" in source) "object" else "val"

    val body = source.replace(": {", " {")
        .replace("\n    ", "\n    val ")
        .replace("<any>", "<*>")
        .replace("number", "Double")

    val content = "external $type $body"
    return ConversionResult(name, content)
}

private fun convertFunction(
    source: String,
): ConversionResult {
    val name = source
        .substringBefore(" ")
        .substringBefore(":")
        .substringBefore("<")
        .substringBefore("(")

    val body = source.removePrefix(name)
        .replaceFirst("(", " $name(")
        .replace(" extends ", " : ")
        .replace(" => void", " -> Unit")
        .replace(" => ", " -> ")

    return ConversionResult(name, "external fun " + body)
}

private fun convertType(
    source: String,
): ConversionResult =
    if (" = {\n" in source) {
        convertInterface(source)
    } else {
        convertTypealias(source)
    }

private fun convertTypealias(
    source: String,
): ConversionResult {
    val name = source
        .substringBefore(" ")
        .substringBefore(":")
        .substringBefore("<")
        .substringBefore("(")

    val body = source
        .replace(" extends ", " : ")
        .replace(": RowData>", "/* : RowData */>")
        .replace(" => ", " -> ")

    return ConversionResult(name, "typealias $body")
}

private fun convertInterface(
    source: String,
): ConversionResult {
    val declaration = source.substringBefore(" = {")
        .replace(" extends ", " : ")
    val name = declaration.substringBefore("<")

    val body = "{\n" + convertMembers(source.substringAfter(" = {")) + "\n}\n"
    return ConversionResult(name, "external interface $declaration$body")
}

private fun convertMembers(
    source: String,
): String =
    source.removeSuffix("\n")
        .substringBeforeLast("\n}")
        .trimIndent()
        .splitToSequence("\n")
        .filter { !it.startsWith("_") }
        .map { it.removeSuffix(";") }
        .map { convertMember(it) }
        .joinToString("\n")

private fun convertMember(
    source: String,
): String {
    val optional = source.substringBefore(": ")
        .endsWith("?")

    val name = source.substringBefore(": ")
        .removeSuffix("?")

    var type = source.substringAfter(": ")
        .let { kotlinType(it, name) }

    if (optional) {
        type = if (type.startsWith("(")) {
            "($type)?"
        } else "$type?"
    }

    return "var $name: $type"
}
