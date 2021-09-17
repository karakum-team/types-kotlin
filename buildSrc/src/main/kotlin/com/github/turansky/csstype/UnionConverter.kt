package com.github.turansky.csstype

import com.github.turansky.common.sealedUnionBody
import com.github.turansky.common.unionBody

internal fun tryToUnion(
    name: String,
    body: String,
    enumMode: Boolean,
): ConversionResult? {
    var items = body
        .removePrefix("| ")
        .replace("\n|", " |")
        .split(" | ")

    if (enumMode) {
        if (!items.all { it.startsWith('"') })
            return null

        val enumBody = unionBody(name, items.toUnionValues())
        return ConversionResult(name, enumBody)
    }

    items = items - "(string & {})"
    if (items.first() != "Globals")
        return null

    items = items.drop(1)
    if (items.isEmpty())
        return null

    if (!items.all { it.startsWith('"') })
        return null

    val enumBody = "// Globals\n" + sealedUnionBody(name, items.toUnionValues())
    return ConversionResult(name, enumBody)
}

private fun List<String>.toUnionValues(): List<String> =
    asSequence()
        .map { it.removeSurrounding("\"") }
        .filter { !it.startsWith("-moz-") }
        .filter { !it.startsWith("-ms-") }
        .filter { !it.startsWith("-webkit-") }
        .toList()
