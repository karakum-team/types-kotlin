package com.github.turansky.popper

private val EXCLUDED_NAMES = setOf(
    "Obj",
    "Window",
)

private val NON_CONVERTABLE = setOf(
    "Instance",
    "Modifier",
    "State",
)

internal fun convertInterface(
    declaration: String,
    source: String,
): ConversionResult? {
    val name = declaration.substringBefore("<")
    if (name in EXCLUDED_NAMES)
        return null

    var members = source
        .trimIndent()
        .splitToSequence(";\n")
        .map(::convertParameter)
        .joinToString("\n")

    if (name in NON_CONVERTABLE)
        members = "/*\n$source\n*/"

    val typeParameters = if ("<" in declaration) {
        val parameters = declaration
            .substringAfter("<")
            .substringBefore(">")
            .replace(" extends Obj", "")
            .removePrefix("Name, ")

        "<$parameters>"
    } else ""

    val body = "external interface $name $typeParameters {\n$members\n}"

    return ConversionResult(name, body)
}
