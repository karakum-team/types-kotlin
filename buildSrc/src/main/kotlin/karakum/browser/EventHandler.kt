package karakum.browser

internal const val EVENT_HANDLER = "EventHandler"

private val EVENT_HANDLER_BODY: String = """
sealed external interface EventHandler<in E : Event, out C : EventTarget>

inline fun EventHandler(
    noinline handler: () -> Unit,
): EventHandler<Event, EventTarget> {
    return handler.unsafeCast<EventHandler<Event, EventTarget>>()
}

inline fun <E : Event, C : EventTarget, D> EventHandler(
    noinline handler: (D) -> Unit,
): EventHandler<E, C>
        where D : E,
              D : $HAS_TARGETS<C> {
    return handler.unsafeCast<EventHandler<E, C>>()
}
""".trimIndent()

internal fun EventHandler(): ConversionResult =
    ConversionResult(
        name = EVENT_HANDLER,
        body = EVENT_HANDLER_BODY,
        pkg = "web.events",
    )
