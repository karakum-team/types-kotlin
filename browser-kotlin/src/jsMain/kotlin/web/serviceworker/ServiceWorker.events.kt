// Automatically generated - do not modify!

package web.serviceworker

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : ServiceWorker> C.errorEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("error"))

inline val <C : ServiceWorker> C.stateChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("statechange"))
