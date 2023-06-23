package karakum.browser

internal fun ConversionResult.withComment(
    fullSource: String,
    source: String,
): ConversionResult {
    val commentSource = fullSource.substringBefore("\n$source", "")
        .takeIf { it.endsWith(" */") }
        ?: return this

    val comment = "/**" + commentSource.substringAfterLast("\n/**")
    return copy(body = "$comment\n${body}")
}
