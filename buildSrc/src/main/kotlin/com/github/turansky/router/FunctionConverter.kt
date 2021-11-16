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

    if (body == "")
        return "external fun $name(): $resultType"

    return source
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
