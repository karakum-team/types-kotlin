// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.crypto

import seskar.js.JsValue
import seskar.js.JsVirtual

        @JsVirtual
        external sealed interface KeyFormat {
            companion object {
                @JsValue("jwk")
val jwk : KeyFormat.jwk
@JsValue("pkcs8")
val pkcs8 : KeyFormat.pkcs8
@JsValue("raw")
val raw : KeyFormat.raw
@JsValue("spki")
val spki : KeyFormat.spki
            }
            
            sealed interface jwk : KeyFormat
sealed interface pkcs8 : KeyFormat
sealed interface raw : KeyFormat
sealed interface spki : KeyFormat
        }
