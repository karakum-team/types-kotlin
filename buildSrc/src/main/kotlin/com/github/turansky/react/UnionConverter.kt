package com.github.turansky.react

internal fun convertUnion(
    name: String,
    source: String,
): ConversionResult? {
    if ("<" in name)
        return null

    if (" | '" !in source)
        return null

    return ConversionResult(name, "typealias $name = String")
}
