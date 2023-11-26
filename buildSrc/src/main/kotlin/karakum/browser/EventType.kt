package karakum.browser

internal const val EVENT_TYPE = "EventType"

private val EVENT_TYPE_BODY: String = """
sealed external interface $EVENT_TYPE<out T : Event>

inline fun <T : Event> $EVENT_TYPE(
    value: String,
): $EVENT_TYPE<T> =
    value.unsafeCast<$EVENT_TYPE<T>>()
""".trimIndent()

internal fun EventType(): ConversionResult =
    ConversionResult(
        name = EVENT_TYPE,
        body = EVENT_TYPE_BODY,
        pkg = "web.events",
    )
