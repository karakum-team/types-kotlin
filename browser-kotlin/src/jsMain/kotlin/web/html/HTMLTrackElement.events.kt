// Automatically generated - do not modify!

package web.html

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : HTMLTrackElement> C.cueChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("cuechange"))

inline val <C : HTMLTrackElement> C.errorEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("error"))

inline val <C : HTMLTrackElement> C.loadEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("load"))
