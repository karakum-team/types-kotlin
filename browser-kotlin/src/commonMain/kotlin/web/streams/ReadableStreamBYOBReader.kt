// Automatically generated - do not modify!

package web.streams

import js.buffer.ArrayBufferView
import js.promise.Promise
import js.typedarrays.Uint8Array
import seskar.js.JsAsync
import kotlin.js.JsName

/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/ReadableStreamBYOBReader)
 */
open external class ReadableStreamBYOBReader(
    stream: ReadableStream<Uint8Array<*>>,
) : ReadableStreamGenericReader {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/ReadableStreamBYOBReader/read)
     */
    @JsAsync
    @Suppress("WRONG_EXTERNAL_DECLARATION")
    suspend fun <T : ArrayBufferView<*>> read(view: T): ReadableStreamReadResult<T>

    @JsName("read")
    fun <T : ArrayBufferView<*>> readAsync(view: T): Promise<ReadableStreamReadResult<T>>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/ReadableStreamBYOBReader/releaseLock)
     */
    fun releaseLock()
}
