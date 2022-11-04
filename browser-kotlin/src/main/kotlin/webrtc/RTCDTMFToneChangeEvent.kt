// Automatically generated - do not modify!

@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package webrtc

import web.events.Event
import web.events.EventInit

external interface RTCDTMFToneChangeEventInit : EventInit {
    var tone: String?
}

open external class RTCDTMFToneChangeEvent(
    type: String,
    init: RTCDTMFToneChangeEventInit = definedExternally,
) : Event {
    val tone: String

    companion object
}
