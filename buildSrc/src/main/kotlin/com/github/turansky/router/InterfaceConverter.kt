package com.github.turansky.router

private val CONVERTABLE = setOf(
    "Path",
    "Location",
    "Update",
)

internal fun convertInterface(
    name: String,
    source: String,
): String {
    if (name !in CONVERTABLE)
        return source

    val declaration = source.substringBefore(" {")
        .replace("interface ", "external interface ")
        .replace(" extends ", " : ")

    val members = source.substringAfter(" {\n")
        .substringBefore(";\n}")
        .trimIndent()
        .splitToSequence(";\n")
        .joinToString("\n", transform = ::convertMember)

    return "$declaration {\n$members\n}"
}

private fun convertMember(
    source: String,
): String {
    val comment = source.substringBeforeLast("\n")
    val body = source.substringAfterLast("\n")

    val declaration = convertParameter(body)

    return sequenceOf(comment, declaration)
        .filter { it.isNotEmpty() }
        .joinToString("\n")
}

private fun convertParameter(
    source: String,
): String {
    val name = source.substringBefore(": ")
        .substringBefore("?:")

    var type = kotlinType(source.substringAfter(": "), name)
    if ("?: " in source && !type.endsWith("?"))
        type += "?"

    return "var $name: $type"
}
