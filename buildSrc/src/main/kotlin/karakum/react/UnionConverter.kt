package karakum.react

import karakum.common.sealedUnionBody

internal fun convertUnion(
    name: String,
    source: String,
): ConversionResult? {
    if ("<" in name)
        return null

    val values = if (source.startsWith(" \"")) {
        source.trim()
            .splitToSequence(" | ")
            .filterNot { it == QUOTES }
            .map { it.removeSurrounding("\"") }
            .toList()
    } else if (" | '" in source) {
        source.removePrefix("\n")
            .trimIndent()
            .splitToSequence("\n")
            .map { it.removePrefix("| ") }
            .filterNot { it == QUOTES }
            .filterNot { it == "(string & {})" }
            .map { it.removeSurrounding("'") }
            .toList()
    } else {
        return null
    }

    return convertUnion(name, values)
}

internal fun convertUnion(
    name: String,
    values: List<String>,
): ConversionResult =
    ConversionResult(
        name = name,
        body = sealedUnionBody(name, values),
    )
