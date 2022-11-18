// Automatically generated - do not modify!

package web.http

import kotlinx.js.ArrayBuffer
import web.buffer.Blob
import kotlin.js.Promise

sealed external interface Body {
    val body: ReadableStream<Uint8Array>?
    val bodyUsed: Boolean
    fun arrayBuffer(): Promise<ArrayBuffer>
    fun blob(): Promise<Blob>
    fun formData(): Promise<FormData>
    fun json(): Promise<any>
    fun text(): Promise<String>
}
