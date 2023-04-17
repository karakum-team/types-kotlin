package karakum.csstype

import karakum.browser.MEDIA_QUERY
import karakum.common.ConversionResult
import karakum.common.kebabToCamel
import karakum.common.sealedUnionBody
import karakum.common.unionBody
import java.util.*

private const val RESOLUTION = "Resolution"

private val RESOLUTION_UNITS = listOf(
    "dpi",
    "dpcm",
    "dppx",
    // "x",
)

private const val MEDIA_TYPE = "MediaType"

private val MEDIA_TYPE_VALUES = listOf(
    "all",
    "print",
    "screen",
)

private fun MediaType(): ConversionResult =
    ConversionResult(
        name = MEDIA_TYPE,
        body = unionBody(MEDIA_TYPE, /* MEDIA_QUERY, */ MEDIA_TYPE_VALUES),
    )

private const val __RATIO__ = "--ratio--"

private val MEDIA_FEATURES = listOf(
    MediaFunction("any-hover", "Hover"),
    MediaFunction("any-pointer", "Pointer"),
    MediaFunction("aspect-ratio", __RATIO__),
    MediaFunction("color", "Int", minmax = true),
    MediaOption("color-gamut", "srgb", "p3", "rec2020"),
    MediaFunction("color-index", "Int", minmax = true),
    MediaOption("display-mode", "fullscreen", "standalone", "minimal-ui", "browser", "window-controls-overlay"),
    MediaOption("dynamic-range", "standard", "high"),
    MediaOption("forced-colors", "none", "active"),
    MediaBoolean("grid"),
    MediaFunction("height", LENGTH, minmax = true),
    MediaOption("hover", "none", "hover"),
    MediaOption("inverted-colors", "none", "inverted"),
    MediaBoolean("monochrome"),
    MediaOption("orientation", "portrait", "landscape"),
    MediaOption("overflow-block", "none", "scroll", "optional-paged", "paged"),
    MediaOption("overflow-inline", "none", "scroll"),
    MediaOption("pointer", "none", "coarse", "fine"),
    MediaOption("prefers-color-scheme", "light", "dark"),
    MediaOption("prefers-contrast", "no-preference", "more", "less", "custom"),
    MediaOption("prefers-reduced-motion", "no-preference", "reduce"),
    MediaFunction("resolution", RESOLUTION, minmax = true),
    MediaOption("scripting", "none", "initial-only", "enabled"),
    MediaOption("update", "none", "slow", "fast"),
    MediaFunction("video-dynamic-range", "DynamicRange"),
    MediaFunction("width", LENGTH, minmax = true),
)

private fun Resolution(): ConversionResult {
    val declarations = sequenceOf(
        "sealed interface $RESOLUTION",
    ) + RESOLUTION_UNITS.map { name ->
        unitsExtension(RESOLUTION, name, name)
    }

    return ConversionResult(RESOLUTION, declarations.joinToString("\n\n"))
}

private sealed interface MediaFeature

private class MediaBoolean(
    val name: String,
) : MediaFeature

private class MediaOption(
    val name: String,
    vararg val values: String,
) : MediaFeature

private class MediaFunction(
    val name: String,
    val type: String,
    val minmax: Boolean = false,
) : MediaFeature

internal fun mediaTypes(): Sequence<ConversionResult> {
    val unions = MEDIA_FEATURES
        .filterIsInstance<MediaOption>()
        .map { option ->
            val name = option.name.kebabToCamel()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            val body = sealedUnionBody(
                name = name,
                values = option.values.toList(),
            )

            ConversionResult(name, body)
        }

    return unions.asSequence()
        .plus(MediaType())
        .plus(Resolution())
}
