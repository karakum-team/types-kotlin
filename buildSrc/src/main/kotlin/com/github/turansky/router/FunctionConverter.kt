package com.github.turansky.router

internal fun convertFunction(
    name: String,
    source: String,
): String {
    if ("Props): " in source)
        return convertComponent(name, source)

    if ("{" in source)
        return source

    val body = source
        .substringAfter("(")
        .substringBefore("): ")

    val resultType = source
        .substringAfter("): ")
        .substringBeforeLast(";")
        .let { kotlinType(it, name) }

    val parameters = body.splitToSequence(", ")
        .filter { it.isNotEmpty() }
        .map(::convertParameter)
        .joinToString(",\n")

    return "external fun $name($parameters): $resultType"
}

private fun convertParameter(
    source: String,
): String {
    val (name, type) = source.split("?: ", ": ")

    return name + ": " + kotlinType(type, name) +
            if ("?: " in source) " = definedExternally" else ""
}

private fun convertComponent(
    name: String,
    source: String,
): String {
    var propsType = source
        .substringBefore("): ")
        .substringAfterLast(": ")

    if (name == "Route")
        propsType = "react.Props /* $propsType */"

    return "external val $name: react.FC<$propsType>"
}
