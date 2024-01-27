package karakum.browser

internal const val EVENT_TARGET = "EventTarget"

private val EVENT_TARGET_BODY: String = """
open external class EventTarget {
    fun <T : Event> addEventListener(
        type: EventType<T>,
        callback: EventHandler<T>,
        options: AddEventListenerOptions? = definedExternally,
    )

    inline fun <T : Event> addEventHandler(
        type: EventType<T>,
        noinline handler: EventHandler<T>,
    ): () -> Unit =
        addEventHandler(
            target = this,
            type = type,
            handler = handler,
        )

    inline fun <T : Event> addEventHandler(
        type: EventType<T>,
        options: AddEventListenerOptions?,
        noinline handler: EventHandler<T>,
    ): () -> Unit =
        addEventHandler(
            target = this,
            type = type,
            handler = handler,
            options = options,
        )

    fun <T : Event> removeEventListener(
        type: EventType<T>,
        callback: EventHandler<T>,
        options: EventListenerOptions? = definedExternally,
    )

    fun dispatchEvent(
        event: Event,
    ): Boolean
}

@PublishedApi
internal fun <T : Event> addEventHandler(
    target: EventTarget,
    type: EventType<T>,
    handler: EventHandler<T>,
): () -> Unit =
    addEventHandler(
        target = target,
        type = type,
        handler = handler,
        options = undefined,
    )

@PublishedApi
internal fun <T : Event> addEventHandler(
    target: EventTarget,
    type: EventType<T>,
    handler: EventHandler<T>,
    options: AddEventListenerOptions?,
): () -> Unit {
    target.addEventListener(
        type = type,
        callback = handler,
        options = options,
    )

    return {
        target.removeEventListener(
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
