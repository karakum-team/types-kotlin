// Automatically generated - do not modify!

package actions.glob

import js.promise.Promise
import js.promise.await
import js.collections.ReadonlyMap
import js.core.BigInt
import js.core.JsLong
import js.objects.Record
import js.array.ReadonlyArray
import js.core.Void
import js.errors.JsError
import node.buffer.Buffer
import node.http.IncomingHttpHeaders
import node.http.OutgoingHttpHeaders
import web.url.URL

import actions.http.client.HttpClient
import actions.http.client.HttpClientResponse

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

sealed external interface Globber {
    /**
     * Returns the search path preceding the first glob segment, from each pattern.
     * Duplicates and descendants of other paths are filtered out.
     *
     * Example 1: The patterns `/foo/ *` and `/bar/ *` returns `/foo` and `/bar`.
     *
     * Example 2: The patterns `/foo/ *` and `/foo/bar/ *` returns `/foo`.
     */
    fun getSearchPaths(): ReadonlyArray<String>
    /**
     * Returns files and directories matching the glob patterns.
     *
     * Order of the results is not guaranteed.
     */
    fun glob(): Promise<ReadonlyArray<String>>
    /**
     * Returns files and directories matching the glob patterns.
     *
     * Order of the results is not guaranteed.
     */
    fun globGenerator(): Any /* AsyncGenerator<string, void> */
}
