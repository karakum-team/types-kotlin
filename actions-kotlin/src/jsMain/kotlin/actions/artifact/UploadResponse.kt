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

sealed external interface UploadResponse {
    /**
     * The name of the artifact that was uploaded
     */
    var artifactName: String
    /**
     * A list of all items that are meant to be uploaded as part of the artifact
     */
    var artifactItems: ReadonlyArray<String>
    /**
     * Total size of the artifact in bytes that was uploaded
     */
    var size: Number
    /**
     * A list of items that were not uploaded as part of the artifact (includes queued items that were not uploaded if
     * continueOnError is set to false). This is a subset of artifactItems.
     */
    var failedItems: ReadonlyArray<String>
}
