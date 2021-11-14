package com.github.turansky.router

import com.github.turansky.common.UnionConstant
import com.github.turansky.common.jsName

private const val DELIMITER = "//--delimiter--//"

internal data class ConversionResult(
    val name: String,
    val body: String,
    // temp field
    val ready: Boolean,
)

internal fun convertDefinitions(
    source: String,
): Sequence<ConversionResult> {
    return source
        .replace("\n/**\n", "\n$DELIMITER\n/**\n")
        .replace("\nexport ", "\n$DELIMITER\nexport ")
        .replace("\n */\n$DELIMITER\n", "\n */\n")
        .splitToSequence("\n$DELIMITER\n")
        .map { it.replace("\ninterface ", "\n\nexport interface ") }
        .map { it.replace("\ndeclare const ", "\n\nexport declare const ") }
        .flatMap { it.splitToSequence("\n\n") }
        .map { content ->
            val name = content.substringAfter(" */\n")
                .substringAfter("export ")
                .substringBefore(" extends ")
                .substringBefore(": ")
                .substringBefore("(")
                .substringBefore("<")
                .substringBefore(" = {")
                .substringBefore(" {")
                .substringBefore(" = ")
                .substringAfterLast(" ")

            val body = content
                .splitToSequence("export declare ", "export ")
                .filter { it.isNotEmpty() }
                .joinToString("")

            convert(name, body)
        }
}

private fun convert(
    name: String,
    source: String,
): ConversionResult {
    val bodySource = source.substringAfter(" */\n")
    val comment = source.removeSuffix(bodySource).removeSuffix("\n")

    val type = bodySource.substringBefore(" ")
    val body = when (type) {
        "const" -> convertConst(name, bodySource)
        "function" -> convertFunction(name, bodySource)
        "type" -> convertType(name, bodySource)
        "interface" -> convertInterface(name, bodySource)
        "enum" -> convertEnum(bodySource)

        else -> TODO()
    }

    return ConversionResult(
        name = name,
        body = sequenceOf(comment, body).joinToString("\n"),
        ready = body != bodySource
    )
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
    val body = source.substringAfter(" = ")
        .removeSuffix(";")

    val alias = when {
        name == "Params" -> "kotlinext.js.Record<String, String>"

        body == "string" -> "String"
        body == "object | null" -> "Any?"
        body == "[string, string]" -> "kotlinext.js.Tuple<String, String>"

        body.startsWith("Partial<") -> "Any // $body"
        body.startsWith("Omit<") -> "Any // $body"
        body.startsWith("string | ") -> "String // $body"

        else -> null
    }

    if (alias != null)
        return "typealias $name = $alias"

    return source
}

private fun convertInterface(
    name: String,
    source: String,
): String {
    return source
}

private fun convertEnum(
    source: String,
): String {
    val constants = source.splitToSequence(" = \"")
        .zipWithNext { a, b ->
            val name = a.substringAfterLast(" ")
            val value = b.substringBefore("\"")
            UnionConstant(name, name, value)
        }
        .toList()

    val annotations = jsName(constants)

    return constants.fold(source) { acc, item ->
        acc.replace(" = \"${item.value}\"", "")
    }.replace("enum ", "$annotations\nenum class ")
        .replace("\n}", ",\n\n;\n}")
}
