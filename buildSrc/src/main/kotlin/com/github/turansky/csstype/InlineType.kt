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

    return replace("$start$originalBody;", "")
        .replace("DataType.$name", body)
}
