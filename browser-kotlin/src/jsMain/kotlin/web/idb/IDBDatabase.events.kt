// Automatically generated - do not modify!

package web.idb

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : IDBDatabase> C.abortEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("abort"))

inline val <C : IDBDatabase> C.closeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("close"))

inline val <C : IDBDatabase> C.errorEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("error"))

inline val <C : IDBDatabase> C.versionChangeEvent: EventInstance<IDBVersionChangeEvent, C, EventTarget>
    get() = EventInstance(this, EventType("versionchange"))
