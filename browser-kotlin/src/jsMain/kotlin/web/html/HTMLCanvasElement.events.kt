// Automatically generated - do not modify!

package web.html

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : HTMLCanvasElement> C.contextLostEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("contextlost"))

inline val <C : HTMLCanvasElement> C.contextRestoredEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("contextrestored"))
