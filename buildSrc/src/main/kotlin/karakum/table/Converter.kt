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
    val content = "external $type ${source.replace(": {", " {")}"
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

    return ConversionResult(name, "typealias " + source)
}

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source
        .substringBefore(" ")
        .substringBefore(":")
        .substringBefore("<")
        .substringBefore("(")

    return ConversionResult(name, "external interface " + source)
}
