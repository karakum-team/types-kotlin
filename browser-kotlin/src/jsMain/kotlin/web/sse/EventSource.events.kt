// Automatically generated - do not modify!

package web.sse

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType
import web.messaging.MessageEvent

inline val <C : EventSource> C.errorEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("error"))

inline val <C : EventSource> C.messageEvent: EventInstance<MessageEvent<Any?>, C, EventTarget>
    get() = EventInstance(this, EventType("message"))

inline val <C : EventSource> C.openEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("open"))
