package karakum.browser

import org.gradle.configurationcache.extensions.capitalized

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
        tagDictionary("HTML", source),
        tagDictionary("SVG", source),
        ConversionResult(
            name = HTML_TAG_NAME,
            body = tagNameBody(HTML_TAG_NAME, "HTMLElement"),
            pkg = "web.html",
        ),
        ConversionResult(
            name = SVG_TAG_NAME,
            body = tagNameBody(SVG_TAG_NAME, "SVGElement"),
            pkg = "web.svg",
        ),
    )
}

private fun tagDictionary(
    name: String,
    source: String,
): ConversionResult {
    val elementType = name + "Element"
    val groupTagName = name.toLowerCase().capitalized() + "TagName"

    val members = source
        .substringAfter("interface ${elementType}TagNameMap {\n")
        .substringBefore("\n}")
        .trimIndent()
        .splitToSequence("\n")
        .joinToString("\n\n") { line ->
            var (tagName, tagType) = line
                .removePrefix("\"")
                .removeSuffix(";")
                .split("\": ")

            val propertyName = when (tagName) {
                "object",
                "var",
                -> "`$tagName`"

                else -> tagName
            }

            """
            inline val $propertyName: $groupTagName<$tagType>
                get() = $groupTagName("$tagName")
            """.trimIndent()
        }

    val body = "object $name {\n$members\n}"

    return ConversionResult(
        name = name,
        body = body,
        pkg = "web.${name.toLowerCase()}",
    )
}
