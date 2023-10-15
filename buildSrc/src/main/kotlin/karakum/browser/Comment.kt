package karakum.browser

internal fun String.withComment(
    fullSource: String,
    source: String,
): String {
    val commentSource = fullSource.substringBefore("\n$source", "")
        .takeIf { it.endsWith(" */") }
        ?: return this

    val comment = "/**" + commentSource.substringAfterLast("\n/**")
    return "$comment\n${this}"
}

internal fun ConversionResult.withComment(
    fullSource: String,
    source: String,
): ConversionResult {
    val newBody = body.withComment(
        fullSource = fullSource,
        source = source,
    )

    return if (body != newBody) {
        copy(body = newBody)
    } else this
}
