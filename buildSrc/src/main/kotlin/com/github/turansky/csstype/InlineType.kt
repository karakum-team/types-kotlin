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
        .inlineType("AnimateableFeature")
        .inlineType("EastAsianVariantValues")
        .inlineType("Quote")
        .inlineType("ContentList")
        .inlineType("CubicBezierTimingFunction")
        .inlineType("StepTimingFunction")
        .inlineType("EasingFunction")
        .inlineType("SingleTransition")

private fun String.inlineType(
    name: String,
): String {
    val declaration = when (name) {
        "SingleTransition" -> "$name<TTime>"
        else -> name
    }
    val start = "\n\n  type $declaration ="

    val originalBody = substringAfter(start).substringBefore(";\n")
    val body = originalBody
        .removePrefix("\n")
        .trimIndent()
        .removePrefix("| ")
        .replace("\n| ", " | ")
        .removeSuffix(" | (string & {})")

    return replace("$start$originalBody;", "")
        .replace("DataType.$name", body)
        .let {
            when (name) {
                "Quote",
                "CubicBezierTimingFunction",
                "StepTimingFunction",
                "EasingFunction",
                -> it.replace(" $name | ", " $body | ")

                else -> it
            }
        }
}
