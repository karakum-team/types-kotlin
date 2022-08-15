// Automatically generated - do not modify!

package node.stream

sealed external interface ReadableOptions : StreamOptions<Readable> {
    var encoding: node.buffer.BufferEncoding?
    val read: ((
        this: Readable,
        size: Number,
    ) -> Unit)?
}
