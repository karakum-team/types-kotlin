package com.github.turansky.popper

internal fun convertModifier(
    source: String,
): ConversionResult? {
    val (nameSource, optionsType) = source
        .substringAfter(" = Modifier<")
        .substringBefore(">")
        .split(", ")

    val id = nameSource.removeSurrounding("\"")
    val name = id.capitalize()

    if (optionsType != "{}")
        return null

    val body = """
        @JsName("'$id'")
        external val $name: popper.core.ModifierName<Nothing?>
    """.trimIndent()

    return ConversionResult(name, body)
}
