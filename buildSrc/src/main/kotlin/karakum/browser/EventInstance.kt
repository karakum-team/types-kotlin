package karakum.browser

internal const val EVENT_INSTANCE = "EventInstance"

// language=kotlin
private val EVENT_INSTANCE_BODY: String = """
import kotlinx.coroutines.*
import kotlin.coroutines.resume

class EventInstance<out E : Event, out C : EventTarget, out T : EventTarget>(
    internal val target: C,
    internal val type: EventType<E, C>,
)

// addHandler
fun <E : Event, C : EventTarget, T : EventTarget> EventInstance<E, C, T>.addHandler(
    handler: EventHandler<E, C, T>,
    options: AddEventListenerOptions? = undefined,
): () -> Unit =
    target.addEventHandler(
        type = type,
        options = options,
        handler = handler,
    )

fun <E : Event, C : EventTarget, T : EventTarget, D> EventInstance<E, C, T>.addHandler(
    handler: (D) -> Unit,
): () -> Unit
        where D : E,
              D : HasTargets<C, T> =
    addHandler(
        handler = EventHandler(handler),
    )

fun <E : Event, C : EventTarget, T : EventTarget, D> EventInstance<E, C, T>.addHandler(
    options: AddEventListenerOptions?,
    handler: (D) -> Unit,
): () -> Unit
        where D : E,
              D : HasTargets<C, T> =
    addHandler(
        handler = EventHandler(handler),
        options = options,
    )

// subscribe
suspend fun <E : Event, C : EventTarget, T : EventTarget> EventInstance<E, C, T>.subscribe(
    handler: EventHandler<E, C, T>,
    options: AddEventListenerOptions? = undefined,
): Job =
    CoroutineScope(currentCoroutineContext()).launch {
        suspendCancellableCoroutine<Nothing> { continuation ->
            val unsubscribe = addHandler(handler, options)

            continuation.invokeOnCancellation {
                unsubscribe()
            }
        }
    }

suspend fun <E : Event, C : EventTarget, T : EventTarget, D> EventInstance<E, C, T>.subscribe(
    handler: (D) -> Unit,
): Job
        where D : E,
              D : HasTargets<C, T> =
    subscribe(
        handler = EventHandler(handler),
    )

suspend fun <E : Event, C : EventTarget, T : EventTarget, D> EventInstance<E, C, T>.subscribe(
    options: AddEventListenerOptions?,
    handler: (D) -> Unit,
): Job
        where D : E,
              D : HasTargets<C, T> =
    subscribe(
        handler = EventHandler(handler),
        options = options,
    )

// once
suspend fun <E : Event, C : EventTarget, T : EventTarget, D> EventInstance<E, C, T>.once(): D
        where D : E,
              D : HasTargets<C, T> {
    return suspendCancellableCoroutine { continuation ->
        val unsubscribe = addHandler(
            handler = continuation::resume,
            options = AddEventListenerOptions(once = true),
        )

        continuation.invokeOnCancellation {
            unsubscribe()
        }
    }
}
""".trimIndent()

internal fun EventInstance(): ConversionResult =
    ConversionResult(
        name = EVENT_INSTANCE,
        body = EVENT_INSTANCE_BODY,
        pkg = "web.events",
    )
