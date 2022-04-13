package karakum.browser

internal const val EVENT_TYPE = "EventType"

private val BODY = """
sealed external interface $EVENT_TYPE<T : Event>

inline fun <T : Event> $EVENT_TYPE(
    value: String,
): $EVENT_TYPE<T> =
    value.unsafeCast<$EVENT_TYPE<T>>()
""".trimIndent()

internal fun eventType(): ConversionResult =
    ConversionResult(
        name = EVENT_TYPE,
        body = BODY,
    )
