// Automatically generated - do not modify!

package web.abort

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : AbortSignal> C.abortEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("abort"))
