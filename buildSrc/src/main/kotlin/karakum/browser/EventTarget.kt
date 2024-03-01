package karakum.browser

internal const val EVENT_TARGET = "EventTarget"

// language=kotlin
private val EVENT_TARGET_BODY: String = """
open external class EventTarget {
    internal fun addEventListener(
        type: EventType<*>,
        callback: Function<Unit>,
        options: AddEventListenerOptions? = definedExternally,
    )

    internal fun removeEventListener(
        type: EventType<*>,
        callback: Function<Unit>,
        options: EventListenerOptions? = definedExternally,
    )

    fun dispatchEvent(
        event: Event<*>,
    ): Boolean
}

fun <T : EventTarget, E : Event<T>> T.addEventListener(
    type: EventType<E>,
    handler: EventHandler<E>,
    options: AddEventListenerOptions? = undefined,
) {
    addEventListener(
        type = type,
        callback = handler,
        options = options,
    )
}

fun <T : EventTarget, E : Event<T>> T.removeEventListener(
    type: EventType<E>,
    handler: EventHandler<E>,
    options: AddEventListenerOptions? = undefined,
) {
    removeEventListener(
        type = type,
        callback = handler,
        options = options,
    )
}

fun <T : EventTarget, E : Event<T>> T.addEventHandler(
    type: EventType<E>,
    handler: EventHandler<E>,
): () -> Unit =
    addEventHandler(
        type = type,
        options = undefined,
        handler = handler,
    )

fun <T : EventTarget, E : Event<T>> T.addEventHandler(
    type: EventType<E>,
    options: AddEventListenerOptions?,
    handler: EventHandler<E>,
): () -> Unit {
    addEventListener(
        type = type,
        callback = handler,
        options = options,
    )

    return {
        removeEventListener(
            type = type,
            callback = handler,
            options = options,
        )
    }
}
""".trimIndent()

internal fun EventTarget(): ConversionResult =
    ConversionResult(
        name = EVENT_TARGET,
        body = EVENT_TARGET_BODY,
        pkg = "web.events",
    )
