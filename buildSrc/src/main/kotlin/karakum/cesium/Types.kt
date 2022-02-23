package karakum.cesium

internal fun isTypeAlias(
    source: String,
): Boolean =
    source.split(" = ")
        .get(1)
        .startsWith("(")

internal fun typeDeclaration(
    source: String,
    top: Boolean,
): String {
    val (name, body) = source.split(" = ")
    return if (body.startsWith("(")) {
        "typealias ${applyCallbackFix(name)} = ${typeBody(body)}"
    } else {
        val modifier = if (top) "external" else ""
        "$modifier interface $name {\n${optionsBody(body)}\n}"
    }
}

internal fun applyCallbackFix(
    source: String,
): String =
    when (source) {
        "foveatedInterpolationCallback",
        "updateCallback",
        -> source.capitalize()

        else -> source
    }

private fun typeBody(body: String): String {
    val (params, returnType) = body
        .removePrefix("(")
        .removeSuffix(";")
        .split(") => ")

    val parameters = params.splitToSequence(",")
        .filter { it.isNotEmpty() }
        .map(::Parameter)
        .onEach { it.supportDefault = false }
        .map { it.toCode() }
        .joinToString(", ")

    return "($parameters) -> ${kotlinType(returnType)}"
}

private fun optionsBody(
    body: String,
): String {
    val source = body.removePrefix("{\n")
        .substringBeforeLast("\n")
        .trimIndent()

    if ("[key: string]: UniformSpecifier;" in source)
        println(source)

    return source
        .splitToSequence(";")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .map { Parameter(it) }
        .onEach { it.supportDefault = false }
        .map { "var ${it.toCode()}" }
        .joinToString("\n")
}

