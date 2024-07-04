package karakum.browser

internal const val EVENT_INSTANCE = "EventInstance"

// language=kotlin
private val EVENT_INSTANCE_BODY: String = """
class EventInstance<out E : Event, out C : EventTarget, out T : EventTarget>(
    internal val target: C,
    internal val type: EventType<E, C>,
)

// event handler
fun <E : Event, C : EventTarget, T : EventTarget> EventInstance<E, C, T>.addHandler(
    handler: EventHandler<E, C, T>,
): () -> Unit =
    target.addEventHandler(
        type = type,
        handler = handler,
    )

fun <E : Event, C : EventTarget, T : EventTarget> EventInstance<E, C, T>.addHandler(
    options: AddEventListenerOptions?,
    handler: EventHandler<E, C, T>,
): () -> Unit =
    target.addEventHandler(
        type = type,
        options = options,
        handler = handler,
    )

// event + targets
fun <E : Event, C : EventTarget, T : EventTarget, D> EventInstance<E, C, T>.addHandler(
    handler: (D) -> Unit,
): () -> Unit
        where D : E,
              D : HasTargets<C, T> =
    target.addEventHandler(
        type = type,
        handler = handler,
    )

fun <E : Event, C : EventTarget, T : EventTarget, D> EventInstance<E, C, T>.addHandler(
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
""".trimIndent()

internal fun EventInstance(): ConversionResult =
    ConversionResult(
        name = EVENT_INSTANCE,
        body = EVENT_INSTANCE_BODY,
        pkg = "web.events",
    )
