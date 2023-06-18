// Automatically generated - do not modify!

package web.streams

import kotlin.js.Promise

sealed external class ReadableStreamDefaultReader<R> :
    ReadableStreamGenericReader {
    fun read(): Promise<ReadableStreamReadResult<R>>
    fun releaseLock()
}
