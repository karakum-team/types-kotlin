// Automatically generated - do not modify!

package node.stream

sealed external interface DuplexOptions : ReadableOptions, WritableOptions {
    var allowHalfOpen: Boolean?
    var readableObjectMode: Boolean?
    var writableObjectMode: Boolean?
    var readableHighWaterMark: Number?
    var writableHighWaterMark: Number?
    var writableCorked: Number?
    val construct: ((
        this: Duplex,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
    val read: ((
        this: Duplex,
        size: Number,
    ) -> Unit)?
    val write: ((
        this: Duplex,
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

    val final: ((
        this: Duplex,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
    val destroy: ((
        this: Duplex,
        error: Error?,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
}
