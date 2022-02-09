package karakum.popper

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

    if (source == "[key in Placement]?: Offsets")
        return ConversionResult(
            name = name,
            body = "typealias $name = kotlinx.js.Record<Placement, Offsets>",
        )

    var members = source
        .splitToSequence(";\n")
        .map(::convertParameter)
        .joinToString("\n")

    if (name == "State")
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
