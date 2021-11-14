package com.github.turansky.router

internal fun convertType(
    name: String,
    source: String,
): String {
    val body = source.substringAfter(" = ")
        .removeSuffix(";")

    val alias = when {
        name == "Params" -> "kotlinext.js.Record<String, String>"

        body == "string" -> "String"
        body == "object | null" -> "Any?"
        body == "[string, string]" -> "kotlinext.js.Tuple<String, String>"

        body.startsWith("Partial<") -> "Any // $body"
        body.startsWith("Omit<") -> "Any // $body"
        body.startsWith("string | ") -> "String // $body"

        else -> null
    }

    if (alias != null)
        return "typealias $name = $alias"

    return source
}
