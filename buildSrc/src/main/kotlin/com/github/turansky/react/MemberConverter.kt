package com.github.turansky.react

internal fun convertMembers(
    source: String,
    final: Boolean,
): String {
    val content = source
        .substringAfter("{\n", "")
        .trimIndent()
        .replace("\n\n", "\n")

    if (content.isEmpty())
        return ""

    return content.removeSuffix(";")
        .splitToSequence(";\n")
        .joinToString("\n") {
            convertMember(it, final)
        }
}

private fun convertMember(
    source: String,
    final: Boolean,
): String {
    if ("; // " in source) {
        if ("\n// " in source) {
            return source.splitToSequence("\n// ")
                .mapIndexed { index, item -> if (index == 0) item else "// " + item }
                .map { convertMember(it, final) }
                .joinToString("\n")
        } else if (!source.startsWith("// ") && source.count { it == '\n' } == 1) {
            return source.splitToSequence("\n")
                .map { convertMember(it, final) }
                .joinToString("\n")
        }
    }

    if ("\n" in source) {
        if (!source.startsWith("/*") && !source.startsWith("//"))
            return convertMember(source.replace("\n", ""), final)

        var comment = source.substringBeforeLast("\n")
        if (comment == "/** @deprecated */")
            comment = """@Deprecated("Will be removed soon!")"""

        return comment + "\n" + convertMember(source.substringAfterLast("\n"), final)
    }

    return if ("(" in source) {
        convertMethod(source)
    } else {
        convertProperty(source, final)
    }
}

private fun convertProperty(
    source: String,
    final: Boolean,
): String {
    val name = source.substringBefore(": ")
        .removeSuffix("?")
        .removeSurrounding("'")

    val id = when (name) {
        "is", "as", "typeof", "in" -> "`$name`"
        else -> name
    }

    val sourceType = source.substringAfter(": ")
        .replace("EventTarget & T", "T")
    val type = kotlinType(sourceType, name)
    val keyword = if (final) "val" else "var"
    return "$keyword $id: $type"
}

private fun convertMethod(
    source: String,
): String {
    val name = source.substringBefore("(")

    val params = source.substringAfter("(")
        .substringBefore("): ")
    val parameters = if (params.isNotEmpty()) {
        params.splitToSequence(", ")
            .joinToString(", ") {
                val (pname, ptype) = it.split(": ")
                "$pname: ${kotlinType(ptype, pname)}"
            }
    } else ""

    val returnType = kotlinType(source.substringAfter("): "), name)
    val returns = if (returnType != UNIT) ": $returnType" else ""

    return "fun $name($parameters)$returns"
}
