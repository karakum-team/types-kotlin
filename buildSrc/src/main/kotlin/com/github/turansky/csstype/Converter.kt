package com.github.turansky.csstype

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
    val ready: Boolean,
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
                content.startsWith("namespace ") -> convertNamespace(content)
                else -> sequenceOf(convertDefinition(name, content))
            }
        }
}

internal fun convertNamespace(
    source: String,
): Sequence<ConversionResult> {
    return source
        .substringAfter("{\n")
        .substringBefore("\n}")
        .trimIndent()
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
                name.contains("Hyphen") -> null
                name.contains("Fallback") -> null
                else -> convertDefinition(name, content)
            }
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

    val comment = if ("\n" in body) "/*\n$body\n*/" else "// $body"

    return ConversionResult(
        name,
        "$comment\nsealed external interface $declaration",
        true,
    )
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
        return ConversionResult(
            name,
            "sealed external interface $declaration\n: $parentType",
            true,
        )
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

    return ConversionResult(
        name,
        "sealed external interface $declaration{\n$body\n}\n",
        false,
    )
}
