package com.github.turansky.router

import com.github.turansky.common.UnionConstant
import com.github.turansky.common.jsName

internal fun convertEnum(
    source: String,
): String {
    val constants = source.splitToSequence(" = \"")
        .zipWithNext { a, b ->
            val name = a.substringAfterLast(" ")
            val value = b.substringBefore("\"")
            UnionConstant(name, name, value)
        }
        .toList()

    val annotations = jsName(constants)

    return constants.fold(source) { acc, item ->
        acc.replace(" = \"${item.value}\"", "")
    }.replace("enum ", "$annotations\nenum class ")
        .replace("\n}", ",\n\n;\n}")
}
