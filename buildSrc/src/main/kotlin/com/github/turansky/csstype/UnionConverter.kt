package com.github.turansky.csstype

import com.github.turansky.common.sealedUnionBody
import com.github.turansky.common.unionBody

private val LENGTH_UNIONS = setOf(
    "MaskPosition",
    "ObjectPosition",
    "Offset",
    "OffsetAnchor",
    "PerspectiveOrigin",

    "BackgroundPosition",
    "BackgroundPositionX",
    "BackgroundPositionY",
    "TextDecorationThickness",
    "TransformOrigin",
    "VerticalAlign",

    "Flex",
    "FlexBasis",
    "Gap",
    "LetterSpacing",
    "Perspective",
    "Translate",
    "WordSpacing",

    "MaskSize",
    "BackgroundSize",

    "FontSize",
    "FontSmooth",

    "OverflowClipMargin",

    "Background",
    "Mask",
)

internal fun tryToUnion(
    name: String,
    body: String,
    enumMode: Boolean,
): ConversionResult? {
    var items = body
        .removePrefix("| ")
        .replace("\n|", " |")
        .split(" | ")
        .distinct()

    if (enumMode && name != "Color") {
        if (!items.all { it.startsWith('"') })
            return null

        val enumBody = unionBody(name, items.toUnionValues())
        return ConversionResult(name, enumBody)
    }

    items = items - "(string & {})"
    if (items[0] != "Globals" && items[0] != "NamedColor")
        return null

    var parentType = items[0]
    items = items.drop(1)

    items = when (name) {
        "TextDecoration",
        -> items - "TLength"

        "Flex",
        "MaskBorder",
        -> items - "(number & {})"
        else -> items
    }

    if (items.isEmpty())
        return null

    when {
        items[0] == "DataType.Color" && items.size >= 2 -> {
            items = items.drop(1)
            parentType = COLOR_PROPERTY
        }

        "TLength" in items && items.size >= 2 && name in LENGTH_UNIONS -> {
            items = items - "TLength"
            parentType = LENGTH_PROPERTY
        }

        "TTime" in items && items.size >= 2 -> {
            items = items - "TTime"
            parentType = TIME_PROPERTY
        }
    }

    if (!items.all { it.startsWith('"') })
        return null

    val enumBody = "// $parentType\n" + sealedUnionBody(name, items.toUnionValues())
    return ConversionResult(name, enumBody)
}

internal fun List<String>.toUnionValues(): List<String> =
    asSequence()
        .map { it.removeSurrounding("\"") }
        .filter { !it.startsWith("-moz-") }
        .filter { !it.startsWith("-ms-") }
        .filter { !it.startsWith("-webkit-") }
        .filter { !it.startsWith(":-khtml-") }
        .filter { !it.startsWith(":-moz-") }
        .filter { !it.startsWith("::-moz-") }
        .filter { !it.startsWith(":-ms-") }
        .filter { !it.startsWith("::-ms-") }
        .filter { !it.startsWith(":-webkit") }
        .filter { !it.startsWith("::-webkit") }
        .toList()
