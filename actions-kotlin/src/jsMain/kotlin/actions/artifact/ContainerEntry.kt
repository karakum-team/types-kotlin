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

sealed external interface ContainerEntry {
    var containerId: Number
    var scopeIdentifier: String
    var path: String
    var itemType: String
    var status: String
    var fileLength: Number?
    var fileEncoding: Number?
    var fileType: Number?
    var dateCreated: String
    var dateLastModified: String
    var createdBy: String
    var lastModifiedBy: String
    var itemLocation: String
    var contentLocation: String
    var fileId: Number?
    var contentId: String
}
