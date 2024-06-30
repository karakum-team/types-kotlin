// Automatically generated - do not modify!

package web.idb

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : IDBRequest<*>> C.errorEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("error"))

inline val <C : IDBRequest<*>> C.successEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("success"))
