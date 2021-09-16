package com.github.turansky.csstype

internal fun String.inlineTypes(): String =
    inlineType("CompatAuto")
        .inlineType("DisplayOutside")
        .inlineType("DisplayInside")
        .inlineType("DisplayInternal")
        .inlineType("DisplayLegacy")
        .inlineType("SelfPosition")
        .inlineType("ContentDistribution")
        .inlineType("ContentPosition")
        .inlineType("SingleAnimationDirection")
        .inlineType("SingleAnimationFillMode")
        .inlineType("Box")
        .inlineType("FontStretchAbsolute")
        .inlineType("Attachment")
        .inlineType("RepeatStyle")
        .inlineType("GenericFamily")
        .inlineType("CompositingOperator")
        .inlineType("MaskingMode")

private fun String.inlineType(
    name: String,
): String {
    val start = "\n\n  type $name ="
    val originalBody = substringAfter(start).substringBefore(";\n")
    val body = originalBody
        .removePrefix("\n")
        .trimIndent()
        .removePrefix("| ")
        .replace("\n| ", " | ")
        .removeSuffix(" | (string & {})")

    return replace("$start$originalBody;", "")
        .replace("DataType.$name", body)
}
