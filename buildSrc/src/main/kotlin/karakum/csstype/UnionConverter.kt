package karakum.csstype

import karakum.common.ConversionResult
import karakum.common.UnionConstant
import karakum.common.sealedUnionBody
import karakum.common.unionBodyByConstants

private const val AUTO_VALUE = "\"auto\""
private const val NONE_VALUE = "\"none\""
private val GEOMETRY_POSITION_ITEMS = GEOMETRY_POSITION_VALUES.map { "\"$it\"" }

private val LENGTH_UNIONS = setOf(
    "MaskPosition",
    "ObjectPosition",
    "Offset",
    "OffsetAnchor",
    "OffsetPosition",
    "PerspectiveOrigin",

    "BackgroundPosition",
    "BackgroundPositionX",
    "BackgroundPositionY",
    "TextDecorationThickness",
    "TransformOrigin",
    "VerticalAlign",

    FLEX,
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

    "Marks",
    "LineStyle",

    "FontDisplay",
    "SpeakAs",

    GRID_LINE,
)

private val VALID_PARENT = setOf(
    "Globals",
    NAMED_COLOR,
    "TLength"
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
                val constants = items.toUnionValues()
                    .map { UnionConstant(it, it, NAMED_COLOR_MAP.getValue(it)) }

                unionBodyByConstants(name, constants)
                    .replaceFirst(NAMED_COLOR, "$NAMED_COLOR: Color")
            }

            else -> sealedUnionBody(name, items.toUnionValues())
        }

        return ConversionResult(name, enumBody)
    }

    items = items - "(string & {})"
    var parentType = items[0]

    when {
        parentType in VALID_PARENT
        -> items = items.drop(1)

        name in EXCLUDED_ENUMS
        -> parentType = ""

        else -> return null
    }

    items = when (name) {
        "TextDecoration",
        -> items - "TLength"

        FLEX,
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

    var comment = when (parentType) {
        NAMED_COLOR,
        "",
        -> ""

        else -> "// $parentType\n"
    }

    if (AUTO_VALUE in items) {
        comment += "// $AUTO\n"
        items = items - AUTO_VALUE
    }

    if (NONE_VALUE in items) {
        comment += "// $NONE\n"
        items = items - NONE_VALUE
    }

    if (items == GEOMETRY_POSITION_ITEMS) {
        comment += "// $GEOMETRY_POSITION\n"
        items = emptyList()
    }

    if (items.isEmpty())
        return ConversionResult(
            name = name,
            body = comment + "sealed external interface $name",
        )

    return ConversionResult(
        name = name,
        body = comment + sealedUnionBody(name, items.toUnionValues()),
    )
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
