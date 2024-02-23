// Automatically generated - do not modify!

package actions.core

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

sealed external interface SummaryImageOptions {
    /**
     * The width of the image in pixels. Must be an integer without a unit.
     * (optional)
     */
    var width: String?
    /**
     * The height of the image in pixels. Must be an integer without a unit.
     * (optional)
     */
    var height: String?
}
