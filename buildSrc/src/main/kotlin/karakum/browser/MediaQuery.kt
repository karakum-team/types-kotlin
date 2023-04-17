package karakum.browser

internal const val MEDIA_QUERY: String = "MediaQuery"

// language=kotlin
private val MEDIA_QUERY_BODY = """
external interface $MEDIA_QUERY

inline fun $MEDIA_QUERY(
    value: String,
): $MEDIA_QUERY =
    value.unsafeCast<$MEDIA_QUERY>()

infix fun $MEDIA_QUERY.and(
    other: $MEDIA_QUERY,
): $MEDIA_QUERY =
    $MEDIA_QUERY("${'$'}this and ${'$'}other")

infix fun $MEDIA_QUERY.or(
    other: $MEDIA_QUERY,
): $MEDIA_QUERY =
    $MEDIA_QUERY("${'$'}this or ${'$'}other")

inline fun not(
    query: $MEDIA_QUERY,
): $MEDIA_QUERY =
    $MEDIA_QUERY("(not (${'$'}query))")
""".trimIndent()

internal fun MediaQuery(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = MEDIA_QUERY,
            body = MEDIA_QUERY_BODY,
            pkg = "web.cssom",
        ),
    )
