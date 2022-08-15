// Automatically generated - do not modify!

package node.stream

sealed external interface ReadableOptions : StreamOptions<Stream /* Readable */> {
    var encoding: node.buffer.BufferEncoding?

    /* this: Readable */
    val read: ((size: Number) -> Unit)?
}
