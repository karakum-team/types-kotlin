package karakum.browser

internal const val HTML_TAG_NAME = "HtmlTagName"
internal const val SVG_TAG_NAME = "SvgTagName"

private fun tagNameBody(
    tagType: String,
    elementType: String,
): String = """
sealed external interface $tagType<T : $elementType>

inline fun <T : $elementType> $tagType(
    tagName: String,
): $tagType<T> =
    tagName.unsafeCast<$tagType<T>>()
""".trimIndent()

internal fun tagNames(
    source: String,
): Sequence<ConversionResult> {
    return sequenceOf(
        ConversionResult(
            name = HTML_TAG_NAME,
            body = tagNameBody(HTML_TAG_NAME, "HTMLElement"),
            pkg = "dom.html",
        ),
        ConversionResult(
            name = SVG_TAG_NAME,
            body = tagNameBody(SVG_TAG_NAME, "SVGElement"),
            pkg = "dom.svg",
        ),
    )
}
