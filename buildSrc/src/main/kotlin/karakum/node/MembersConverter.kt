package karakum.node

internal fun convertMembers(
    source: String,
): String {
    if (source.isEmpty())
        return ""

    return source.removeSuffix(";")
        .replace(";\n  ", "-111-\n  ")
        .replace(";\n}", "-222-\n}")
        .splitToSequence(";\n")
        .map { it.replace("-111-\n  ", ";\n  ") }
        .map { it.replace("-222-\n}", ";\n}") }
        .map { convertMember(it) }
        .joinToString("\n")
}

private fun convertMember(
    source: String,
): String {
    var comment = source.substringBeforeLast("\n */\n", "")
        .ifEmpty { null }
        ?.let { "$it\n */" }

    var body = if (comment != null) {
        source.substringAfter("$comment\n")
    } else source

    if (body.startsWith("// TODO: ")) {
        comment = comment + "\n    " + body.substringBefore("\n")
        body = body.substringAfter("\n")
    }

    val content = if (body.startsWith("constructor(") || body.startsWith("new (")) {
        convertConstructor(body)
    } else if (isProperty(body)) {
        convertProperty(body)
    } else {
        convertMethod(body)
    }

    comment = comment?.replace("* /*\n", "* ---\n")

    return sequenceOf(comment, content)
        .filterNotNull()
        .joinToString("\n")
}

private fun isProperty(
    source: String,
): Boolean =
    ("(" !in source) || (source.indexOf(":") < source.indexOf("("))

private fun convertProperty(
    source: String,
): String {
    if (source.startsWith("static "))
        return "/* STATIC */\n" + convertProperty(source.removePrefix("static "))

    val modifier = if (source.startsWith("readonly ")) "val" else "var"
    val body = source.removePrefix("readonly ")

    val name = body.substringBefore(": ").removeSuffix("?")
    var type = kotlinType(body.substringAfter(": "), name)

    if (body.startsWith("$name?"))
        type = type.addOptionality()

    return "$modifier $name: $type"
}

private fun convertConstructor(
    source: String,
): String {
    val parametersSource = source
        .substringAfter("(")
        .substringBeforeLast("): ")
        .removeSuffix(")")

    val parameters = when {
        parametersSource.isNotEmpty()
        -> parametersSource
            .splitToSequence(", ")
            .joinToString(",\n") {
                convertParameter(it, false)
            }

        else -> ""
    }

    return "constructor($parameters)"
}

private fun convertMethod(
    source: String,
): String {
    if ("{" in source) {
        return "\n// HIDDEN METHOD START\n/*\n$source\n*/\n// HIDDEN METHOD END\n".prependIndent("    ")
    }

    if (source.startsWith("(time?: ["))
        return "    /* $source */"

    if (source.startsWith("static "))
        return "/* STATIC */\n" + convertMethod(source.removePrefix("static "))

    val name = source.substringBefore("(")
        .substringBefore("<")
        .removeSuffix("?")
        .ifEmpty { "/* native */ invoke" }

    val typeParameters = source.substringBefore("(")
        .substringAfter("<", "")
        .let { if (it.isNotEmpty()) "<$it" else "" }
        .replace(" extends ", " : ")
        .replace("NodeJS.ArrayBufferView", "ArrayBufferView")
        .replace(" = Buffer", "")

    val parametersSource = source
        .substringAfter("(")
        .substringBeforeLast("): ")

    val optional = source.startsWith("$name?")
    val parameters = when {
        parametersSource.isNotEmpty()
        -> parametersSource
            .splitToSequence(", ")
            .joinToString(",\n") {
                convertParameter(it, optional)
            }

        else -> ""
    }

    val returnType = kotlinType(source.substringAfterLast("): "), name)

    if (optional)
        return "val $name: (($parameters) -> $returnType)?"

    val returnDeclaration = if (returnType != UNIT) {
        ": $returnType"
    } else ""

    var result = "fun $typeParameters $name($parameters)$returnDeclaration"
    if (" => " in result)
        result = result
            .replace("value: any", "value: Any")
            .replace(" => void", "-> Unit")

    return result
}

private fun convertParameter(
    source: String,
    lambdaMode: Boolean,
): String {
    val name = source
        .removePrefix("...")
        .substringBefore(": ")
        .removeSuffix("?")

    val type = kotlinType(source.substringAfter(": "), name)
    val declaration = if (source.startsWith("$name?:")) {
        if (lambdaMode) {
            type.addOptionality()
        } else {
            "$type = definedExternally"
        }
    } else type

    var result = "$name: $declaration"
    if (source.startsWith("..."))
        result = "vararg $result"

    return result
}
