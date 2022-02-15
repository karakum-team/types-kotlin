package karakum.csstype

internal const val URL = "Url"

// source - https://developer.mozilla.org/ru/docs/Web/CSS/url()
private val PARENT_TYPES = setOf(
    "Background",
    "BackgroundImage",
    "Border",
    "BorderImage",
    "BorderImageSource",
    "Content",
    "Cursor",
    "Filter",
    "ListStyle",
    "ListStyleImage",
    "Mask",
    "MaskImage",
    "OffsetPath",
)

internal fun Url(): ConversionResult =
    ConversionResult(
        name = URL,
        body = "sealed external interface $URL:\n" +
                PARENT_TYPES.joinToString(",\n")
    )
