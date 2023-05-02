package karakum.browser

private const val MEDIA_QUERY: String = "MediaQuery"

internal fun String.applyMediaQueryPatch(): String =
    replace("var media: String", "var media: $MEDIA_QUERY")

internal fun String.applyMediaQueryFunctionPatch(): String =
    replace("query: String", "query: $MEDIA_QUERY")
