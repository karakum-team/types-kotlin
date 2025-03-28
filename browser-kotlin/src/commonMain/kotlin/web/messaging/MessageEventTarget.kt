// Automatically generated - do not modify!

@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
)

package web.messaging

import web.events.EventHandler
import web.events.EventTarget
import kotlin.js.definedExternally

external interface MessageEventTarget :
    EventTarget {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/DedicatedWorkerGlobalScope/message_event)
     */
    var onmessage: EventHandler<MessageEvent<*>, MessageEventTarget, MessageEventTarget>?
        get() = definedExternally
        set(value) = definedExternally

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/DedicatedWorkerGlobalScope/messageerror_event)
     */
    var onmessageerror: EventHandler<MessageEvent<*>, MessageEventTarget, MessageEventTarget>?
        get() = definedExternally
        set(value) = definedExternally
}
