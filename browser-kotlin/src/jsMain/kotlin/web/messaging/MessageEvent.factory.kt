// Automatically generated - do not modify!

@file:Suppress(
    "NOTHING_TO_INLINE",
)

package web.messaging

import web.events.EventTarget
import web.events.EventType

inline fun MessageEvent(
    type: EventType<MessageEvent<D, *>>,
): MessageEvent<D, *> =
    MessageEvent<EventTarget?>(
        type = type,
    )

inline fun MessageEvent(
    type: EventType<MessageEvent<D, *>>,
    init: MessageEventInit<D>,
): MessageEvent<D, *> =
    MessageEvent<EventTarget?>(
        type = type,
        init = init,
    )
