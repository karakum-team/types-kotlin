// Automatically generated - do not modify!

@file:Suppress(
    "NOTHING_TO_INLINE",
)

package web.events

inline fun CustomEvent(
    type: EventType<CustomEvent<D, *>>,
): CustomEvent<D, *> =
    CustomEvent<EventTarget?>(
        type = type,
    )

inline fun CustomEvent(
    type: EventType<CustomEvent<D, *>>,
    init: CustomEventInit<D>,
): CustomEvent<D, *> =
    CustomEvent<EventTarget?>(
        type = type,
        init = init,
    )
