// Automatically generated - do not modify!

@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.crypto

import seskar.js.JsUnion
import seskar.js.JsValue

@JsUnion
sealed external interface KeyFormat {
    companion object {
        @JsValue("jwk")
        val jwk: jwk

        @JsValue("pkcs8")
        val pkcs8: pkcs8

        @JsValue("raw")
        val raw: raw

        @JsValue("spki")
        val spki: spki
    }

    interface jwk
    interface pkcs8
    interface raw
    interface spki
}
