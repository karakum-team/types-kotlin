// Automatically generated - do not modify!

@file:Suppress(
    "NOTHING_TO_INLINE",
)

package web.idb

import web.events.EventTarget
import web.events.EventType

inline fun IDBVersionChangeEvent(
    type: EventType<IDBVersionChangeEvent<*>>,
): IDBVersionChangeEvent<*> =
    IDBVersionChangeEvent<EventTarget?>(
        type = type,
    )

inline fun IDBVersionChangeEvent(
    type: EventType<IDBVersionChangeEvent<*>>,
    init: IDBVersionChangeEventInit,
): IDBVersionChangeEvent<*> =
    IDBVersionChangeEvent<EventTarget?>(
        type = type,
        init = init,
    )
