package karakum.typescript

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionsFile: File,
): Sequence<ConversionResult> =
    definitionsFile.readText()
        .splitToSequence("declare namespace ts {\n")
        .drop(1)
        .map { it.substringBefore("\n}\n") }
        .map { it.trimIndent() }
        .flatMap { convertDefinitions(it) }

private const val DELIMITER = "<!--DELIMITER-->"

private val CONVERTER_MAP = mapOf(
    "const" to ::convertConst,
    "function" to ::convertFunction,
    "type" to ::convertType,
    "enum" to ::convertEnum,
    "interface" to ::convertInterface,
    "class" to ::convertClass,
)

private val KEYWORDS = CONVERTER_MAP.keys

private fun convertDefinitions(
    source: String,
): Sequence<ConversionResult> {
    var content = source.replace("\n/**", "\n$DELIMITER/**")
    for (keyword in KEYWORDS) {
        content = content
            .replace("\nexport $keyword ", "\n${DELIMITER}$keyword ")
            .replace("\n$keyword ", "\n${DELIMITER}$keyword ")
    }

    var comment: String? = null
    val results = mutableListOf<ConversionResult>()

    content.splitToSequence(DELIMITER)
        .filter { it.isNotEmpty() }
        .map { it.removePrefix("export ") }
        .map { it.removeSuffix("\n") }
        .map { it.removeSuffix(";") }
        .forEach { part ->
            if (part.startsWith("/**")) {
                comment = part
            } else {
                results += convertDefinition(comment, part)
            }
        }

    return results.asSequence()
}

private fun convertDefinition(
    comment: String?,
    source: String,
): ConversionResult {
    val name = source.substringAfter(" ")
        .substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    val type = source.substringBefore(" ")
    val content = CONVERTER_MAP.getValue(type)(name, source)

    val body = sequenceOf(comment, content)
        .filterNotNull()
        .joinToString("\n")

    return ConversionResult(name, body)
}

private fun convertConst(
    name: String,
    source: String,
): String {
    return source
}

private fun convertFunction(
    name: String,
    source: String,
): String {
    return source
}

private fun convertType(
    name: String,
    source: String,
): String {
    return source
}

private fun convertEnum(
    name: String,
    source: String,
): String {
    return source
}

private fun convertInterface(
    name: String,
    source: String,
): String {
    return source
}

private fun convertClass(
    name: String,
    source: String,
): String {
    return source
}


