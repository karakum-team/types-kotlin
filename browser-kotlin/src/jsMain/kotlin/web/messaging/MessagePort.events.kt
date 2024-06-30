// Automatically generated - do not modify!

package web.messaging

import web.events.Event
import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType

inline val <C : MessagePort> C.closeEvent: EventInstance<Event, C, EventTarget>
    get() = EventInstance(this, EventType("close"))

inline val <C : MessagePort> C.messageEvent: EventInstance<MessageEvent<Any?>, C, EventTarget>
    get() = EventInstance(this, EventType("message"))

inline val <C : MessagePort> C.messageErrorEvent: EventInstance<MessageEvent<Any?>, C, EventTarget>
    get() = EventInstance(this, EventType("messageerror"))
