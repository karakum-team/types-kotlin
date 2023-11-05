// Automatically generated - do not modify!

@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package react.dom.html

import seskar.js.JsUnion
import seskar.js.JsValue

@JsUnion
sealed external interface ImgDecoding {
    companion object {
        @JsValue("async")
        val async: ImgDecoding

        @JsValue("auto")
        val auto: ImgDecoding

        @JsValue("sync")
        val sync: ImgDecoding
    }
}
