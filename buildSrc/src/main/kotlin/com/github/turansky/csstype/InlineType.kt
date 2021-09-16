package com.github.turansky.csstype

internal fun String.inlineType(
    name: String,
): String {
    val start = "\n\n  type $name ="
    val originalBody = substringAfter(start).substringBefore(";\n")
    val body = originalBody
        .removePrefix("\n")
        .trimIndent()
        .removePrefix("| ")
        .replace("\n| ", " | ")

    return replace("$start$originalBody;", "")
        .replace("DataType.$name", body)
}
