// Automatically generated - do not modify!

package web.codecs

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : VideoEncoder> C.dequeueEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("dequeue"))
