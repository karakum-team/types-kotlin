// Automatically generated - do not modify!

@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.rtc

import seskar.js.JsValue

sealed external interface RTCIceRole {
    companion object {
        @JsValue("controlled")
        val controlled: RTCIceRole

        @JsValue("controlling")
        val controlling: RTCIceRole

        @JsValue("unknown")
        val unknown: RTCIceRole
    }
}
