// Automatically generated - do not modify!

package actions.artifact

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

sealed external interface UploadResults {
    /**
     * The size in bytes of data that was transferred during the upload process to the actions backend service. This takes into account possible
     * gzip compression to reduce the amount of data that needs to be transferred
     */
    var uploadSize: Number
    /**
     * The raw size of the files that were specified for upload
     */
    var totalSize: Number
    /**
     * An array of files that failed to upload
     */
    var failedItems: ReadonlyArray<String>
}
