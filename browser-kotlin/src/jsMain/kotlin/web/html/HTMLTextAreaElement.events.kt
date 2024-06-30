// Automatically generated - do not modify!

package web.html

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : HTMLTextAreaElement> C.invalidEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("invalid"))

inline val <C : HTMLTextAreaElement> C.selectEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("select"))
