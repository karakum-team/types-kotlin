// Automatically generated - do not modify!

@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.cssom.atrule

import seskar.js.JsValue
import seskar.js.JsVirtual
import web.cssom.MediaQuery

@JsVirtual
sealed external interface MediaType : MediaQuery {
    companion object {
        @JsValue("all")
        val all: MediaType

        @JsValue("print")
        val print: MediaType

        @JsValue("screen")
        val screen: MediaType
    }
}
