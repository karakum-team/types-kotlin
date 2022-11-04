// Automatically generated - do not modify!

@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package webrtc

import web.events.Event
import web.events.EventInit

external interface RTCPeerConnectionIceEventInit : EventInit {
    var candidate: RTCIceCandidate?
    var url: String?
}

open external class RTCPeerConnectionIceEvent(
    type: String,
    init: RTCPeerConnectionIceEventInit = definedExternally,
) : Event {
    val candidate: RTCIceCandidate?

    companion object
}
