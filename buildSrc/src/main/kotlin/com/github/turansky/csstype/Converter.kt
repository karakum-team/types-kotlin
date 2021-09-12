package com.github.turansky.csstype

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionsFile: File,
): Sequence<ConversionResult> {
    return definitionsFile.readText()
        .removePrefix("export {};\n")
        .splitToSequence("\nexport ", "\ndeclare ")
        .drop(1)
        .flatMap { content ->
            val name = content.substringAfter(" ")
                .substringBefore(" ")
                .substringBefore("<")

            when {
                name.startsWith("Svg") -> emptySequence()
                name.startsWith("Obsolete") -> emptySequence()
                name.startsWith("Vendor") -> emptySequence()
                name.contains("Hyphen") -> emptySequence()
                name.contains("Fallback") -> emptySequence()
                name == "StandardShorthandProperties" -> emptySequence()
                content.startsWith("namespace AtRule ") -> convertNamespace(content) + convertNamespaceTypes(content)
                content.startsWith("namespace ") -> convertNamespace(content)
                else -> sequenceOf(convertDefinition(name, content))
            }
        }
}

private fun convertNamespace(
    source: String,
): Sequence<ConversionResult> {
    return source
        .substringAfter("{\n")
        .substringBefore("\n}")
        .trimIndent()
        .let { "\n" + it }
        .splitToSequence("\nexport ")
        .drop(1)
        .mapNotNull { content ->
            val name = content.substringAfter(" ")
                .substringBefore(" ")
                .substringBefore("<")

            when {
                name.startsWith("Moz") -> null
                name.startsWith("Ms") -> null
                name.startsWith("Webkit") -> null
                name.contains("Hyphen") && name != "Hyphens" -> null
                name.contains("Fallback") -> null
                else -> convertDefinition(name, content)
            }
        }
}

private val ADDITIONAL_TYPES = setOf(
    "FontDisplay",
    "Size",
    "Inherits",
    "AlignContent",
    "Hyphens",
    "MaxZoom",
    "MinZoom",
    "Orientation",
    "UserZoom",
    "ViewportFit",
    "Range",
    "SpeakAs",
    "System",
)

private fun convertNamespaceTypes(
    source: String,
): Sequence<ConversionResult> {
    return source
        .substringAfter("{\n")
        .substringBefore("\n}")
        .trimIndent()
        .splitToSequence("\ntype ")
        .drop(1)
        .mapNotNull { content ->
            val name = content
                .substringBefore(" ")
                .substringBefore("<")

            if (name in ADDITIONAL_TYPES) {
                convertUnion(name, content)
            } else null
        }
}

private fun convertDefinition(
    name: String,
    source: String,
): ConversionResult {
    val content = source
        .replace("TLength = (string & {}) | 0", "TLength")
        .replace("TTime = string & {}", "TTime")

    if (content.startsWith("type "))
        return convertUnion(name, content)

    return convertInterface(name, content)
}

private fun convertUnion(
    name: String,
    source: String,
): ConversionResult {
    val declaration = source.removePrefix("type ")
        .substringBefore(" =")

    val body = source.substringAfter(" =")
        .removePrefix("\n")
        .trimIndent()
        .removeSuffix(";")

    val comment = if ("\n" in body) "/*\n$body\n*/" else "// $body"

    return ConversionResult(name, "$comment\nsealed external interface $declaration")
}

private fun convertInterface(
    name: String,
    source: String,
): ConversionResult {
    val declaration = source
        .removePrefix("interface ")
        .substringBefore("\n")
        .substringBefore(" {")

    val parentType = source
        .substringBefore("{")
        .substringAfter(" extends ", "")
        .substringBefore(",\n")

    if (parentType.isNotEmpty()) {
        return ConversionResult(name, "sealed external interface $declaration\n: $parentType")
    }

    val body = source.substringAfter("{\n")
        .substringBefore("\n}")
        .trimIndent()
        .splitToSequence("\n")
        .map {
            if ("?: " in it) {
                var (pname, ptype) = it.split("?: ")
                ptype = ptype.removePrefix("Property.").removeSuffix(";")
                if (ptype == "string") ptype = "String"
                "var $pname: $ptype"
            } else it
        }
        .joinToString("\n")
        .replaceIndent("    ")

    return ConversionResult(name, "sealed external interface $declaration{\n$body\n}\n")
}
