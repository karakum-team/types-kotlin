// Automatically generated - do not modify!

package node.stream

sealed external interface TransformOptions : DuplexOptions {
    val construct: ((
        this: Transform,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
    val read: ((
        this: Transform,
        size: Number,
    ) -> Unit)?
    val write: ((
        this: Transform,
        chunk: Any,
        encoding: node.buffer.BufferEncoding,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?

    // HIDDEN METHOD START
    /*
    writev?(
        this: Transform,
        chunks: Array<{
            chunk: any;
            encoding: BufferEncoding;
        }>,
        callback: (error?: Error | null) => void
    ): void
    */
    // HIDDEN METHOD END

    val final: ((
        this: Transform,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
    val destroy: ((
        this: Transform,
        error: Error?,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
    val transform: ((
        this: Transform,
        chunk: Any,
        encoding: node.buffer.BufferEncoding,
        callback: TransformCallback,
    ) -> Unit)?
    val flush: ((
        this: Transform,
        callback: TransformCallback,
    ) -> Unit)?
}
