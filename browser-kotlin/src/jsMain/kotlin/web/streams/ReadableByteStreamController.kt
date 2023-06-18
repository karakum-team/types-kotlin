// Automatically generated - do not modify!

package web.streams

import js.buffer.ArrayBufferView

sealed external class ReadableByteStreamController {
    val byobRequest: ReadableStreamBYOBRequest?
    val desiredSize: Number?
    fun close()
    fun enqueue(chunk: ArrayBufferView)
    fun error(e: Any? = definedExternally)
}
