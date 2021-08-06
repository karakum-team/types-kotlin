package com.github.turansky.react

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> {
    val content = definitionFile.readText()
        .replace("\r\n", "\n")
        .substringAfter("declare namespace React {\n")
        .substringBefore("\n}\n")
        .trimIndent()

    return content.splitToSequence("\ninterface ")
        .drop(1)
        .map { it.substringBefore("\n}\n") }
        .mapNotNull {
            convertInterface(
                name = it.substringBefore(" ")
                    .substringBefore("<"),
                source = it,
            )
        }
}

private fun convertInterface(
    name: String,
    source: String,
): ConversionResult? =
    when {
        name.endsWith("Event") -> convertEventInterface(name, source)
        name.endsWith("Attributes") -> convertAttributesInterface(name, source)
        name == "ReactHTML" -> convertIntrinsicTypes(source)
        else -> null
    }

private fun convertEventInterface(
    name: String,
    source: String,
): ConversionResult =
    ConversionResult(name, "external interface $name")

private fun convertAttributesInterface(
    name: String,
    source: String,
): ConversionResult {
    var declaration = source.substringBefore(" {")
        .replace(" extends ", " : ")

    if (name == "DOMAttributes")
        declaration += ": react.RProps"

    return ConversionResult(name, "external interface $declaration")
}

private fun convertIntrinsicTypes(
    source: String,
): ConversionResult {
    val body = source.substringAfter("{\n")
        .trimIndent()
        .removeSuffix(";")
        .splitToSequence(";\n")
        .map { convertIntrinsicType(it) }
        .joinToString("\n\n")

    return ConversionResult("IntrinsicTypes", body)
}

private fun convertIntrinsicType(
    source: String,
): String {
    val name = source.substringBefore(": ")
    val propsType = source.substringAfter(": DetailedHTMLFactory<")
        .substringBefore(",")
        .replaceFirst("<", "<org.w3c.dom.")
    val type = "IntrinsicType<$propsType>"

    return """
        inline val $name: $type
            get() = "$name".unsafeCast<$type>()
    """.trimIndent()
}

private fun props(propsName: String): String =
    "external interface $propsName: react.RProps"
