// Automatically generated - do not modify!

package web.sockets

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType
import web.messaging.MessageEvent

inline val <C : WebSocket> C.closeEvent: EventInstance<CloseEvent, C, EventTarget>
    get() = EventInstance(this, EventType("close"))

inline val <C : WebSocket> C.errorEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("error"))

inline val <C : WebSocket> C.messageEvent: EventInstance<MessageEvent<Any?>, C, EventTarget>
    get() = EventInstance(this, EventType("message"))

inline val <C : WebSocket> C.openEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("open"))
