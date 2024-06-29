package karakum.browser

internal const val EVENT_INSTANCE = "EventInstance"

// language=kotlin
private val EVENT_INSTANCE_BODY: String = """
class EventInstance<E: Event, in C: EventTarget, in T: EventTarget>(
    private val target: C,
    private val type: EventType<E, C>,
) {
    // event handler
    fun addHandler(
        handler: EventHandler<E, C, T>,
    ): () -> Unit =
        target.addEventHandler(
            type = type,
            handler = handler,
        )

    fun addHandler(
        options: AddEventListenerOptions?,
        handler: EventHandler<E, C, T>,
    ): () -> Unit =
        target.addEventHandler(
            type = type,
            options = options,
            handler = handler,
        )

    // event + targets
    fun <D> addHandler(
        handler: (D) -> Unit,
    ): () -> Unit
            where D : E,
                  D : HasTargets<C, T> =
        target.addEventHandler(
            type = type,
            handler = handler,
        )

    fun <D> addHandler(
        options: AddEventListenerOptions?,
        handler: (D) -> Unit,
    ): () -> Unit
            where D : E,
                  D : HasTargets<C, T> =
        target.addEventHandler(
            type = type,
            options = options,
            handler = handler,
        )
}
""".trimIndent()

internal fun EventInstance(): ConversionResult =
    ConversionResult(
        name = EVENT_INSTANCE,
        body = EVENT_INSTANCE_BODY,
        pkg = "web.events",
    )
