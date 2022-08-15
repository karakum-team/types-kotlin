// Automatically generated - do not modify!

package node.stream

sealed external interface WritableOptions : StreamOptions<Writable> {
    var decodeStrings: Boolean?
    var defaultEncoding: node.buffer.BufferEncoding?
    val write: ((
        this: Writable,
        chunk: Any,
        encoding: node.buffer.BufferEncoding,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?

    // HIDDEN METHOD START
    /*
    writev?(
        this: Writable,
        chunks: Array<{
            chunk: any;
            encoding: BufferEncoding;
        }>,
        callback: (error?: Error | null) => void
    ): void
    */
    // HIDDEN METHOD END

    val final: ((
        this: Writable,
        callback: (error: Error?) -> Unit,
    ) -> Unit)?
}
