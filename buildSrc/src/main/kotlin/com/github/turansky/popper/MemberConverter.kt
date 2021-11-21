package com.github.turansky.popper

internal fun convertParameter(
    source: String,
): String {
    val name = source
        .substringBefore("?: ")
        .substringBefore(": ")
        .removePrefix("readonly ")

    var type = kotlinType(source.substringAfter(": "), name)
    if ("?: " in source && !type.endsWith("?")) {
        if (type.startsWith("(")) {
            type = "($type)?"
        } else {
            type += "?"
        }
    }

    return "var $name: $type"
}
