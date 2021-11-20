package com.github.turansky.popper

private val EXCLUDED_NAMES = setOf(
    "Obj",
    "Window",
)

internal fun convertInterface(
    declaration: String,
    source: String,
): ConversionResult? {
    val name = declaration.substringBefore("<")
    if (name in EXCLUDED_NAMES)
        return null

    val body = "external interface $declaration {\n$source\n}"

    return ConversionResult(name, body)
}
