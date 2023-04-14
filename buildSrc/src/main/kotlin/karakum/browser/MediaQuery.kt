package karakum.browser

internal const val MEDIA_QUERY: String = "MediaQuery"

// language=kotlin
private val MEDIA_QUERY_BODY = """
sealed external interface $MEDIA_QUERY

inline fun $MEDIA_QUERY(
    value: String,
): $MEDIA_QUERY =
    value.unsafeCast<$MEDIA_QUERY>()
""".trimIndent()

internal fun MediaQuery(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = MEDIA_QUERY,
            body = MEDIA_QUERY_BODY,
            pkg = "web.cssom",
        ),
    )
