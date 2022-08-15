// Automatically generated - do not modify!

package node.stream

sealed external interface DuplexOptions : ReadableOptions, WritableOptions {
    var allowHalfOpen: Boolean?
    var readableObjectMode: Boolean?
    var writableObjectMode: Boolean?
    var readableHighWaterMark: Number?
    var writableHighWaterMark: Number?
    var writableCorked: Number?

    /* this: Duplex */
    val construct: ((callback: (error: Error?) -> Unit) -> Unit)?

    /* this: Duplex */
    val read: ((size: Number) -> Unit)?

    /* this: Duplex */
    val write: ((
        chunk: Any,
        encoding: node.buffer.BufferEncoding,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?

    // HIDDEN METHOD START
    /*
    writev?(
        this: Duplex,
        chunks: Array<{
            chunk: any;
            encoding: BufferEncoding;
        }>,
        callback: (error?: Error | null) => void
    ): void
    */
    // HIDDEN METHOD END

    /* this: Duplex */
    val final: ((callback: (error: Error?) -> Unit) -> Unit)?

    /* this: Duplex */
    val destroy: ((
        error: Error?,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
}
