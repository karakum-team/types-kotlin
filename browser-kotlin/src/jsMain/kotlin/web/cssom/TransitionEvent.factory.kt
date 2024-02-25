// Automatically generated - do not modify!

@file:Suppress(
    "NOTHING_TO_INLINE",
)

package web.cssom

import web.events.EventTarget
import web.events.EventType

inline fun TransitionEvent(
    type: EventType<TransitionEvent<*>>,
): TransitionEvent<*> =
    TransitionEvent<EventTarget?>(
        type = type,
    )

inline fun TransitionEvent(
    type: EventType<TransitionEvent<*>>,
    init: TransitionEventInit,
): TransitionEvent<*> =
    TransitionEvent<EventTarget?>(
        type = type,
        init = init,
    )
