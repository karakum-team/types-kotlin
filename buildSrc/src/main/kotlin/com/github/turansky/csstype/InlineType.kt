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
        .inlineType("GeometryBox")
        .inlineType("Position")
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
        .inlineType("BgPosition")
        .inlineType("BgSize")
        .inlineType("AbsoluteSize")
        .inlineType("TrackBreadth")
        .inlineType("VisualBox")

private fun String.inlineType(
    name: String,
): String {
    val declaration = when (name) {
        "BgPosition",
        "BgSize",
        "Position",
        "TrackBreadth",
        -> "$name<TLength>"

        "SingleTransition",
        -> "$name<TTime>"

        else -> name
    }

    val startPrefix = when (name) {
        "AbsoluteSize" -> ""
        else -> "\n\n"
    }

    val start = "$startPrefix  type $declaration ="

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

                "Box",
                -> it.replace(" = $name | ", " = $body | ")

                else -> it
            }
        }
}
