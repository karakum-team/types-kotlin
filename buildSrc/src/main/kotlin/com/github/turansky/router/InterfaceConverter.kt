package com.github.turansky.router

private val CONVERTABLE = setOf(
    "Path",
    "Location",
    "Update",
    "RouteMatch",
)

internal fun convertInterface(
    name: String,
    source: String,
): String {
    when {
        name == "LinkProps" -> return source
        name == "NavLinkProps" -> return source

        name in CONVERTABLE -> Unit
        name.endsWith("Object") -> Unit
        name.endsWith("Options") -> Unit
        name.endsWith("Props") -> Unit
        else -> return source
    }

    val declaration = source.substringBefore(" {")
        .replace("interface ", "external interface ")
        .replace(" extends ", " : ")

    val members = source.substringAfter(" {\n")
        .also { if (it == "}") return declaration }
        .substringBefore(";\n}")
        .trimIndent()
        .splitToSequence(";\n")
        .joinToString("\n", transform = ::convertMember)

    return "$declaration {\n$members\n}"
}

private fun convertMember(
    source: String,
): String {
    val comment = source.substringBeforeLast("\n", "")
    val body = source.substringAfterLast("\n")

    val declaration = convertParameter(body)

    return sequenceOf(comment, declaration)
        .filter { it.isNotEmpty() }
        .joinToString("\n")
}

private fun convertParameter(
    source: String,
): String {
    val name = source
        .substringBefore("?: ")
        .substringBefore(": ")

    var type = kotlinType(source.substringAfter(": "), name)
    if ("?: " in source && !type.endsWith("?"))
        type += "?"

    return "var $name: $type"
}
