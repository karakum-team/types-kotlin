// Automatically generated - do not modify!

@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.rtc

import seskar.js.JsValue

sealed external interface RTCQualityLimitationReason {
    companion object {
        @JsValue("bandwidth")
        val bandwidth: RTCQualityLimitationReason

        @JsValue("cpu")
        val cpu: RTCQualityLimitationReason

        @JsValue("none")
        val none: RTCQualityLimitationReason

        @JsValue("other")
        val other: RTCQualityLimitationReason
    }
}
