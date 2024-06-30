// Automatically generated - do not modify!

package web.html

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : HTMLSelectElement> C.changeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("change"))

inline val <C : HTMLSelectElement> C.invalidEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("invalid"))
