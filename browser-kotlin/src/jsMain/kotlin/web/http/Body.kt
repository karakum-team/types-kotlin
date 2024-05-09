// Automatically generated - do not modify!

@file:Suppress(
    "NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE",
)

package web.http

import js.buffer.ArrayBuffer
import js.promise.Promise
import js.typedarrays.Uint8Array
import web.blob.Blob
import web.form.FormData
import web.streams.ReadableStream

sealed external interface Body {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Request/body)
     */
    val body: ReadableStream<Uint8Array>?
        get() = definedExternally

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Request/bodyUsed)
     */
    val bodyUsed: Boolean
        get() = definedExternally

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Request/arrayBuffer)
     */
    @JsName("arrayBuffer")
    fun arrayBufferAsync(): Promise<ArrayBuffer> = definedExternally

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Request/blob)
     */
    @JsName("blob")
    fun blobAsync(): Promise<Blob> = definedExternally

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Request/formData)
     */
    @JsName("formData")
    fun formDataAsync(): Promise<FormData> = definedExternally

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Request/json)
     */
    @JsName("json")
    fun jsonAsync(): Promise<*> = definedExternally

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Request/text)
     */
    @JsName("text")
    fun textAsync(): Promise<String> = definedExternally
}
