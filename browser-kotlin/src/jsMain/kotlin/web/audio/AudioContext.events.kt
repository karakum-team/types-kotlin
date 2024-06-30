// Automatically generated - do not modify!

package web.audio

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : AudioContext> C.sinkchangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("sinkchange"))
