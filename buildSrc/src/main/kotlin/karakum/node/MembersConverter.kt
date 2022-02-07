package karakum.node

internal fun convertMembers(
    source: String,
): String {
    if (source.isEmpty())
        return ""

    return source.removeSuffix(";")
        .splitToSequence(";\n")
        .map { convertMember(it) }
        .joinToString("\n")
}

private fun convertMember(
    source: String,
): String {
    val comment = source.substringBeforeLast("\n", "")
        .ifEmpty { null }

    val body = source.substringAfterLast("\n")
    val content = if (isProperty(body)) {
        convertProperty(body)
    } else {
        convertMethod(body)
    }

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
    val modifier = if (source.startsWith("readonly ")) "val" else "var"
    val body = source.removePrefix("readonly ")

    val name = body.substringBefore(": ").removeSuffix("?")
    var type = kotlinType(body.substringAfter(": "), name)

    if (body.startsWith("$name?"))
        type = type.addOptionality()

    return "$modifier $name: $type"
}

internal fun convertMethod(
    source: String,
): String {
    val name = source.substringBefore("(")
        .substringBefore("<")
        .removeSuffix("?")
        .ifEmpty { "/* native */ invoke" }

    val typeParameters = source.substringBefore("(")
        .substringAfter("<", "")
        .let { if (it.isNotEmpty()) "<$it" else "" }
        .replace(" extends ", " : ")

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

internal fun convertParameter(
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
