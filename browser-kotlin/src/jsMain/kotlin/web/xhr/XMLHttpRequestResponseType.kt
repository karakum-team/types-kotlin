// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.xhr

import js.objects.jso
import seskar.js.JsValue
import seskar.js.JsVirtual
import web.http.Request
import web.http.Response

        @JsVirtual
        sealed external interface XMLHttpRequestResponseType {
            companion object {
                @JsValue("")    
val none: XMLHttpRequestResponseType
@JsValue("arraybuffer")    
val arraybuffer: XMLHttpRequestResponseType
@JsValue("blob")    
val blob: XMLHttpRequestResponseType
@JsValue("document")    
val document: XMLHttpRequestResponseType
@JsValue("json")    
val json: XMLHttpRequestResponseType
@JsValue("text")    
val text: XMLHttpRequestResponseType
            }
        }
