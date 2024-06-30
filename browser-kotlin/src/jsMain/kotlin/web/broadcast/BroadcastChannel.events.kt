// Automatically generated - do not modify!

package web.broadcast

import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType
import web.messaging.MessageEvent

inline val <C : BroadcastChannel> C.messageEvent: EventInstance<MessageEvent<Any?>, C, EventTarget>
    get() = EventInstance(this, EventType("message"))

inline val <C : BroadcastChannel> C.messageErrorEvent: EventInstance<MessageEvent<Any?>, C, EventTarget>
    get() = EventInstance(this, EventType("messageerror"))
