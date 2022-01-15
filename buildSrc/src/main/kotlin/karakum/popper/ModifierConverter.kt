package karakum.popper

private val EXTENDED_PADDING = """
 | ((arg0: {
        popper: Rect;
        reference: Rect;
        placement: Placement;
    }) => Padding)
""".removeSurrounding("\n")

internal fun convertModifier(
    source: String,
): ConversionResult {
    val (nameSource, optionsType) = source
        .substringAfter(" = Modifier<")
        .substringBefore(">")
        .split(", ")

    val id = nameSource.removeSurrounding("\"")
    val name = id.capitalize()

    val typeParameter = when (optionsType) {
        "Options" -> "${name}Options"
        "{}" -> "Nothing?"
        else -> TODO()
    }

    val modifierBody = """
        @JsName("'$id'")
        external val $name: popper.core.ModifierName<$typeParameter>
    """.trimIndent()

    if (typeParameter == "Nothing?")
        return ConversionResult(name, modifierBody)

    val optionsSource = source
        .replace(EXTENDED_PADDING, "")
        .substringAfter("export declare type Options = {\n")
        .substringBefore(";\n};\n")

    val body = sequenceOf(
        convertInterface(typeParameter, optionsSource)!!.body,
        modifierBody,
    ).joinToString("\n\n")

    return ConversionResult(name, body)
}
