package karakum.react

import karakum.common.removeQuoteSurrounding
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
            .removeQuoteSurrounding()
            .toList()
    } else if (" | '" in source.trim() || " | ${Typography.quote}" in source.trim()) {
        source.removePrefix("\n")
            .trimIndent()
            .splitToSequence("\n")
            .map { it.removePrefix("| ") }
            .filterNot { it == "(string & {})" }
            .removeQuoteSurrounding()
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
