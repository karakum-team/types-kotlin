// Automatically generated - do not modify!

package web.idb

import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : IDBOpenDBRequest> C.blockedEvent: EventInstance<IDBVersionChangeEvent, C, EventTarget>
    get() = EventInstance(this, EventType("blocked"))

inline val <C : IDBOpenDBRequest> C.successEvent: EventInstance<IDBVersionChangeEvent, C, EventTarget>
    get() = EventInstance(this, EventType("success"))

inline val <C : IDBOpenDBRequest> C.upgradeneededEvent: EventInstance<IDBVersionChangeEvent, C, EventTarget>
    get() = EventInstance(this, EventType("upgradeneeded"))
