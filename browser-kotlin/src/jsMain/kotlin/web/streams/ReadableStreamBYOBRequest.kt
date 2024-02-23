// Automatically generated - do not modify!

package web.streams

import js.buffer.ArrayBuffer
import js.buffer.ArrayBufferView
import js.core.JsLong
import web.http.Request
import web.streams.ReadableStream

/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/ReadableStreamBYOBRequest)
 */
sealed external class ReadableStreamBYOBRequest {
/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/ReadableStreamBYOBRequest/view)
 */
val view: ArrayBufferView?
/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/ReadableStreamBYOBRequest/respond)
 */
 fun respond(bytesWritten: JsLong)
/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/ReadableStreamBYOBRequest/respondWithNewView)
 */
 fun respondWithNewView(view: ArrayBufferView)
}
