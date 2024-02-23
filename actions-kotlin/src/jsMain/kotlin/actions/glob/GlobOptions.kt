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

sealed external interface GlobOptions {
    /**
     * Indicates whether to follow symbolic links. Generally should set to false
     * when deleting files.
     *
     * @default true
     */
    var followSymbolicLinks: Boolean?
    /**
     * Indicates whether directories that match a glob pattern, should implicitly
     * cause all descendant paths to be matched.
     *
     * For example, given the directory `my-dir`, the following glob patterns
     * would produce the same results: `my-dir/ **`, `my-dir/`, `my-dir`
     *
     * @default true
     */
    var implicitDescendants: Boolean?
    /**
     * Indicates whether matching directories should be included in the
     * result set.
     *
     * @default true
     */
    var matchDirectories: Boolean?
    /**
     * Indicates whether broken symbolic should be ignored and omitted from the
     * result set. Otherwise an error will be thrown.
     *
     * @default true
     */
    var omitBrokenSymbolicLinks: Boolean?
}
