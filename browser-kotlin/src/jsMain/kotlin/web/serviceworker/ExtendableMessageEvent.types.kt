// Automatically generated - do not modify!

@file:Suppress(
"NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE",
)

package web.serviceworker

import seskar.js.JsValue
import web.events.Event
import web.events.EventType
import web.messaging.MessageEvent

    sealed external interface ExtendableMessageEventTypes {
        @JsValue("message")
val MESSAGE : EventType<ExtendableMessageEvent>
    get() = definedExternally
    }
