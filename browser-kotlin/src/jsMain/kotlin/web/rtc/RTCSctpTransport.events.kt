// Automatically generated - do not modify!

package web.rtc

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : RTCSctpTransport> C.stateChangeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("statechange"))
