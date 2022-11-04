// Automatically generated - do not modify!

@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package webrtc

import web.events.Event
import web.events.EventInit

external interface RTCDataChannelEventInit : EventInit {
    var channel: RTCDataChannel
}

open external class RTCDataChannelEvent(
    type: String,
    init: RTCDataChannelEventInit,
) : Event {
    val channel: RTCDataChannel

    companion object
}
