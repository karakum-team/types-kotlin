// Automatically generated - do not modify!

@file:Suppress(
    "NOTHING_TO_INLINE",
)

package web.serviceworker

import web.events.EventTarget
import web.events.EventType

inline fun ExtendableMessageEvent(
    type: EventType<ExtendableMessageEvent<*>>,
): ExtendableMessageEvent<*> =
    ExtendableMessageEvent<EventTarget?>(
        type = type,
    )

inline fun ExtendableMessageEvent(
    type: EventType<ExtendableMessageEvent<*>>,
    init: ExtendableMessageEventInit,
): ExtendableMessageEvent<*> =
    ExtendableMessageEvent<EventTarget?>(
        type = type,
        init = init,
    )