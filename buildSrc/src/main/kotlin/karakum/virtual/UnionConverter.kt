package karakum.virtual

import karakum.common.Suppress
import karakum.common.suppress
import karakum.common.unionBody

internal fun convertUnion(
    name: String,
    source: String,
): ConversionResult {
    val values = source
        .splitToSequence(" | ")
        .map { it.removeSurrounding("'") }
        .toList()

    return convertUnion(name, values)
}

private fun convertUnion(
    name: String,
    values: List<String>,
): ConversionResult {
    val body = suppress(Suppress.NAME_CONTAINS_ILLEGAL_CHARS) + "\n" + unionBody(name, values)
    return ConversionResult(name, body)
}
