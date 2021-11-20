package com.github.turansky.popper

private val EXCLUDED_NAMES = setOf(
    "Obj",
    "Window",
)

private val CONVERTABLE = setOf(
    "ClientRectObject",
    "EventListeners",
    // "Instance",
    // "Modifier",
    // "ModifierArguments",
    "Offsets",
    // "Options",
    // "OptionsGeneric",
    "Rect",
    "SideObject",
    // "State",
    "StateOffsets",
    "StateRects",
    // "VirtualElement",
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

    if (name !in CONVERTABLE)
        members = source

    val body = "external interface $declaration {\n$members\n}"

    return ConversionResult(name, body)
}
