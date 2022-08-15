// Automatically generated - do not modify!

package node.stream

sealed external interface TransformOptions : DuplexOptions {
    /* this: Transform */
    override val construct: ((callback: (error: Error?) -> Unit) -> Unit)?

    /* this: Transform */
    override val read: ((size: Number) -> Unit)?

    /* this: Transform */
    override val write: ((
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

    /* this: Transform */
    override val final: ((callback: (error: Error?) -> Unit) -> Unit)?

    /* this: Transform */
    override val destroy: ((
        error: Error?,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?

    /* this: Transform */
    val transform: ((
        chunk: Any,
        encoding: node.buffer.BufferEncoding,
        callback: TransformCallback,
    ) -> Unit)?

    /* this: Transform */
    val flush: ((callback: TransformCallback) -> Unit)?
}
