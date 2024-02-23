// Automatically generated - do not modify!

package actions.cache

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

sealed external interface UploadOptions {
    /**
     * Number of parallel cache upload
     *
     * @default 4
     */
    var uploadConcurrency: Number?
    /**
     * Maximum chunk size in bytes for cache upload
     *
     * @default 32MB
     */
    var uploadChunkSize: Number?
}
