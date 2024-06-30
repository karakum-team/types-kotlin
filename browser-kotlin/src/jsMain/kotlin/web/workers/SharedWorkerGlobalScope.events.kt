// Automatically generated - do not modify!

package web.workers

import web.events.EventInstance
import web.events.EventTarget
import web.events.EventType
import web.messaging.MessageEvent

inline val <C : SharedWorkerGlobalScope> C.connectEvent: EventInstance<MessageEvent<Any?>, C, EventTarget>
    get() = EventInstance(this, EventType("connect"))
