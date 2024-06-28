package karakum.browser

internal const val EVENT_HANDLER = "EventHandler"

// language=kotlin
private val EVENT_HANDLER_BODY: String = """
sealed external interface EventHandler<in E : Event, out C : EventTarget, out T : EventTarget>

inline fun EventHandler(
    noinline handler: () -> Unit,
): EventHandler<Event, Nothing, Nothing> {
    return handler.unsafeCast<EventHandler<Event, Nothing, Nothing>>()
}

inline fun <E : Event, C : EventTarget, T : EventTarget, D> EventHandler(
    noinline handler: (D) -> Unit,
): EventHandler<E, C, T>
        where D : E,
              D : $HAS_TARGETS<C, T> {
    return handler.unsafeCast<EventHandler<E, C, T>>()
}
""".trimIndent()

internal fun EventHandler(): ConversionResult =
    ConversionResult(
        name = EVENT_HANDLER,
        body = EVENT_HANDLER_BODY,
        pkg = "web.events",
    )
