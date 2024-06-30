// Automatically generated - do not modify!

package web.canvas

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : OffscreenCanvas> C.contextLostEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("contextlost"))

inline val <C : OffscreenCanvas> C.contextRestoredEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("contextrestored"))
