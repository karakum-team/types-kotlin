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
                content.startsWith("namespace AtRule ") -> convertNamespace(content) + convertNamespaceTypes(content)
                content.startsWith("namespace ") -> convertNamespace(content)
                else -> sequenceOf(convertDefinition(name, content))
            }
        } + Length()
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
                name in SVG_TYPES -> null
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
        .map { it.replace("<TLength>", "") }
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
        .replace("<TLength, TTime>", "")
        .replace("<TLength>", "")
        .replace("<TTime>", "")

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

    if (body == """"false" | "true"""")
        return ConversionResult(name, "typealias $name = Boolean")

    val comment = if ("\n" in body) {
        val values = body
            .splitToSequence("\n")
            .filter { !it.startsWith("| \"-moz-") }
            .filter { !it.startsWith("| \"-ms-") }
            .filter { !it.startsWith("| \"-webkit-") }
            .joinToString("\n")

        "/*\n$values\n*/"
    } else {
        val values = body
            .splitToSequence(" | ")
            .filter { !it.startsWith("\"-moz-") }
            .filter { !it.startsWith("\"-ms-") }
            .filter { !it.startsWith("\"-webkit-") }
            .joinToString(" | ")

        when (values) {
            "Globals | DataType.Color",
            "Globals | DataType.Color | (string & {})",
            -> if (name != "Color") {
                return ConversionResult(name, "typealias $name = Color")
            }
        }

        "// $values"
    }

    return ConversionResult(name, "$comment\nsealed external interface $declaration")
}

private val LINK_REGEX = Regex("( * @see )(.+)")

private val BOLD_1_REGEX = Regex("""(\|\s+)\*\*""")
private val BOLD_2_REGEX = Regex("""\*\*(\s+\|)""")
private val BOLD_3_REGEX = Regex("""\*\*( _\()""")

private val PRE_1_REGEX = Regex("""([\d.]+) _-x-_""")
private val PRE_2_REGEX = Regex("""([\d.]+)\*\* _-x-_""")

private fun convertInterface(
    name: String,
    source: String,
): ConversionResult {
    val declaration = source
        .removePrefix("interface ")
        .substringBefore("\n")
        .substringBefore(" {")

    val extends = source
        .substringBefore("{")
        .substringAfter(" extends ", "")

    if (extends.isNotEmpty()) {
        val parentTypes = extends.replace("\n", "")
            .splitToSequence(",")
            .map { it.trim() }
            .filter { "Vendor" !in it && "Obsolete" !in it && "Svg" !in it }
            .joinToString(", ")

        return ConversionResult(name, "external interface $declaration: $parentTypes")
    }

    val body = source.substringAfter("{\n")
        .substringBefore("\n}")
        .trimIndent()
        .replace("\n * | ", "\n *    | ")
        .replace(" ", " ")
        .replace(LINK_REGEX, """$1<a href="$2">MDN</a>""")
        .replace(BOLD_1_REGEX, "$1  ")
        .replace(BOLD_2_REGEX, "  $1")
        .replace(BOLD_3_REGEX, "  $1")
        .replace(PRE_1_REGEX, "   $1′  ")
        .replace(PRE_2_REGEX, "    $1′   ")
        .splitToSequence("\n")
        .map {
            if ("?: " in it) {
                var (pname, ptype) = it.split("?: ")
                ptype = ptype.removePrefix("Property.").removeSuffix(";")
                if (ptype == "string") ptype = "String"
                "var $pname: $ptype?"
            } else it
        }
        .joinToString("\n")
        .replaceIndent("    ")

    return ConversionResult(name, "external interface $declaration{\n$body\n}")
}
