// Automatically generated - do not modify!

package web.audio

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : AudioScheduledSourceNode> C.endedEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("ended"))
