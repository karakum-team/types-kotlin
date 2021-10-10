package com.github.turansky.csstype

import com.github.turansky.common.ConstData
import com.github.turansky.common.sealedUnionBody
import com.github.turansky.common.unionBody
import com.github.turansky.common.unionBodyByData

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

private val EXCLUDED_ENUMS = setOf(
    "Color",
    "LineWidth",
    "Bleed",
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

    if (enumMode && name !in EXCLUDED_ENUMS) {
        if (!items.all { it.startsWith('"') })
            return null

        val enumBody = when (name) {
            NAMED_COLOR -> {
                val constData = items.toUnionValues()
                    .map { ConstData(it, it, NAMED_COLOR_MAP.getValue(it)) }

                unionBodyByData(name, constData)
                    .replaceFirst(NAMED_COLOR, "$NAMED_COLOR: Color")
            }

            else -> unionBody(name, items.toUnionValues())
        }

        return ConversionResult(name, enumBody)
    }

    items = items - "(string & {})"
    when (items[0]) {
        "Globals",
        NAMED_COLOR,
        "TLength",
        -> Unit

        else -> return null
    }

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

    val comment = when (parentType) {
        NAMED_COLOR -> ""
        else -> "// $parentType\n"
    }

    val enumBody = comment + sealedUnionBody(name, items.toUnionValues())
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
