package karakum.node

import karakum.common.unionBody

internal fun convertUnion(
    name: String,
    source: String,
): ConversionResult? {
    val unionSource = source.removePrefix("\n    | ")
    if (!unionSource.startsWith("'"))
        return null

    val values = unionSource
        .splitToSequence("\n    | ", " | ")
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
