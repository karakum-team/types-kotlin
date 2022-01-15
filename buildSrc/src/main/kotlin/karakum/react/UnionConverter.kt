package karakum.react

import karakum.common.Suppress.NAME_CONTAINS_ILLEGAL_CHARS
import karakum.common.suppress
import karakum.common.unionBody

internal fun convertUnion(
    name: String,
    source: String,
): ConversionResult? {
    if ("<" in name)
        return null

    if (" | '" !in source)
        return null

    val values = source.removePrefix("\n")
        .trimIndent()
        .splitToSequence("\n")
        .map { it.removePrefix("| ") }
        .filter { it != "(string & {})" }
        .map { it.removeSurrounding("'") }
        .toList()

    return convertUnion(name, values)
}

internal fun convertUnion(
    name: String,
    values: List<String>,
): ConversionResult {
    val body = suppress(NAME_CONTAINS_ILLEGAL_CHARS) + "\n" + unionBody(name, values)
    return ConversionResult(name, body)
}
