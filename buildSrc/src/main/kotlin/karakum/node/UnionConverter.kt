package karakum.node

import karakum.common.Suppress.NAME_CONTAINS_ILLEGAL_CHARS
import karakum.common.suppress
import karakum.common.unionBody

internal fun convertUnion(
    name: String,
    source: String,
): ConversionResult? {
    if (!source.startsWith("'"))
        return null

    val values = source
        .splitToSequence(" | ")
        .map { it.removeSurrounding("'") }
        .minus("utf-8") // BufferEncoding
        .minus("ucs-2") // BufferEncoding
        .toList()

    return convertUnion(name, values)
}

internal fun convertUnion(
    name: String,
    values: List<String>,
): ConversionResult {
    val body = unionBody(name, values)
    return ConversionResult(name, body)
}
