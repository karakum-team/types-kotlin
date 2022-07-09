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
            body = "typealias $name = Record<Placement, Offsets>",
        )

    var members = source
        .splitToSequence(";\n")
        .map(::convertParameter)
        .joinToString("\n")

    if (name == "State") {
        members = source
            .splitToSequence("\n")
            .map { line ->
                when {
                    line.startsWith("    ") -> line

                    ": {" in line -> "val " + line.replace(": {", ": dynamic /* {")
                    line.startsWith("};") -> line + " */"

                    else -> {
                        val (propName, propType) = line.removeSuffix(";").split(": ")
                        val type = when (propType) {
                            "boolean" -> "Boolean"
                            "Array<Modifier<any, any>>" -> "ReadonlyArray<Modifier<*>>"
                            else -> propType.replace("any", "*")
                        }

                        "val $propName: $type"
                    }
                }
            }.joinToString("\n")
            .prependIndent("    ")
    }

    val typeParameters = if ("<" in declaration) {
        val parameters = declaration
            .substringAfter("<")
            .substringBefore(">")
            .replace(" extends Obj", "")
            .removePrefix("Name, ")

        "<$parameters>"
    } else ""

    val inherited = when (name) {
        "SideObject" -> ":Padding"
        else -> ""
    }

    val body = "external interface ${name}$inherited $typeParameters {\n$members\n}"

    return ConversionResult(name, body)
}
