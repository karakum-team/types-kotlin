package karakum.browser

internal const val EVENT_TARGET = "EventTarget"

// language=kotlin
private val EVENT_TARGET_BODY: String = """
open external class EventTarget {
    internal fun <E: Event> addEventListener(
        type: EventType<E, *>,
        callback: EventHandler<E, *>,
        options: AddEventListenerOptions? = definedExternally,
    )
    
    internal fun addEventListener(
        type: EventType<*, *>,
        callback: Function<Unit>,
        options: AddEventListenerOptions? = definedExternally,
    )
    
    internal fun <E: Event> removeEventListener(
        type: EventType<E, *>,
        callback: EventHandler<E, *>,
        options: EventListenerOptions? = definedExternally,
    )
    
    internal fun removeEventListener(
        type: EventType<*, *>,
        callback: Function<Unit>,
        options: EventListenerOptions? = definedExternally,
    )

    fun dispatchEvent(
        event: Event,
    ): Boolean
}

// event + targets
fun <E : Event, C : EventTarget> C.addEventListener(
    type: EventType<E, C>,
    handler: EventHandler<E, C>,
    options: AddEventListenerOptions? = undefined,
) {
    addEventListener(
        type = type,
        callback = handler,
        options = options,
    )
}

fun <E : Event, C : EventTarget> C.removeEventListener(
    type: EventType<E, C>,
    handler: EventHandler<E, C>,
    options: AddEventListenerOptions? = undefined,
) {
    removeEventListener(
        type = type,
        callback = handler,
        options = options,
    )
}

fun <E : Event, C : EventTarget> C.addEventHandler(
    type: EventType<E, C>,
    handler: EventHandler<E, C>,
): () -> Unit =
    addEventHandler(
        type = type,
        options = undefined,
        handler = handler,
    )

fun <E : Event, C : EventTarget> C.addEventHandler(
    type: EventType<E, C>,
    options: AddEventListenerOptions?,
    handler: EventHandler<E, C>,
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

// event
fun <E : Event, C : EventTarget> C.addEventListener(
    type: EventType<E, C>,
    handler: (E) -> Unit,
    options: AddEventListenerOptions? = undefined,
) {
    addEventListener(
        type = type,
        callback = handler,
        options = options,
    )
}

fun <E : Event, C : EventTarget> C.removeEventListener(
    type: EventType<E, C>,
    handler: (E) -> Unit,
    options: AddEventListenerOptions? = undefined,
) {
    removeEventListener(
        type = type,
        callback = handler,
        options = options,
    )
}

fun <E : Event, C : EventTarget> C.addEventHandler(
    type: EventType<E, C>,
    handler: (E) -> Unit,
): () -> Unit =
    addEventHandler(
        type = type,
        options = undefined,
        handler = handler,
    )

fun <E : Event, C : EventTarget> C.addEventHandler(
    type: EventType<E, C>,
    options: AddEventListenerOptions?,
    handler: (E) -> Unit,
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
