package karakum.typescript

import karakum.common.UnionConstant
import karakum.common.unionBodyByConstants
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
    val content = CONVERTER_MAP.getValue(type)(name, source.removePrefix("$type "))

    val body = sequenceOf(comment, content)
        .filterNotNull()
        .joinToString("\n")

    return ConversionResult(name, body)
}

private fun convertConst(
    name: String,
    source: String,
): String {
    return "/*\nexternal val $source\n*/"
}

private fun convertFunction(
    name: String,
    source: String,
): String {
    if ("): node is " in source && name != "isIterationStatement")
        return "external fun " +
                source.replace("): node is ", "): Boolean /* node is ")
                    .replace("Node | undefined", "Node?") +
                " */"

    return "/*\nexternal fun $source\n*/"
}

private fun convertType(
    name: String,
    source: String,
): String {
    val (declarationSource, body) = source.split(" = ")

    val declaration = declarationSource
        .replace("extends BuilderProgram", "/* : BuilderProgram */")
        .replace("extends Node", "/* : Node */")


    var content = "typealias $declaration = Any /* $body */"
    if ("<T" in declaration)
        content = "@Suppress(\"UNUSED_TYPEALIAS_PARAMETER\")\n" + content

    return content
}

private fun convertEnum(
    name: String,
    source: String,
): String {
    val constants = source
        .substringAfter("{\n")
        .substringBeforeLast("\n}")
        .trimIndent()
        .splitToSequence(",\n")
        .map {
            val (cname, cvalue) = it.substringAfterLast("\n").split(" = ")
            val comment = it.substringBeforeLast("\n", "").ifEmpty { null }
            UnionConstant(
                kotlinName = cname,
                jsName = cname,
                value = cvalue,
                originalValue = true,
                comment = comment,
            )
        }
        .toList()

    return unionBodyByConstants(name, constants)
}

// TEMP
internal val IGNORED_INTERFACES = setOf(
    "KeywordTypeNode",

    // "KeywordToken",
    "PunctuationToken",

    "NodeArray",
    "SortedArray",
    "SortedReadonlyArray",

    "JsxAttributes",
    "ObjectLiteralExpression",
)

private fun convertInterface(
    name: String,
    source: String,
): String {
    if (name in IGNORED_INTERFACES)
        return "interface $source"

    var declaration = source.substringBefore(" {\n")
        .replace(" extends ", " : ")
        .replace("<string", "<String")

    if (name == "KeywordToken")
        declaration = declaration.replaceFirst("> : ", "> /* : ") + " */"

    val bodySource = source
        .substringAfter("{\n")
        .substringBeforeLast("\n}", "")

    val body = if (bodySource.isNotEmpty()) {
        "    /*\n" + bodySource + "\n    */"
    } else ""

    return "external interface $declaration {\n$body\n}"
}

private fun convertClass(
    name: String,
    source: String,
): String {
    return "external class $source"
}


