// Automatically generated - do not modify!

package node.stream.web

import kotlin.js.Promise

sealed external interface ReadableStreamDefaultReader<R> : ReadableStreamGenericReader {
    fun read(): Promise<ReadableStreamDefaultReadResult<R>>
    fun releaseLock()
}
