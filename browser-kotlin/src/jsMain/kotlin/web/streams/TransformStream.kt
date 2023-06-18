// Automatically generated - do not modify!

package web.streams

sealed external class TransformStream<
        I,
        O,
        > {
    val readable: ReadableStream<O>
    val writable: WritableStream<I>
}
