// Automatically generated - do not modify!

package actions.http.client

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

sealed external interface RequestOptions {
    var headers: node.http.OutgoingHttpHeaders?
    var socketTimeout: Number?
    var ignoreSslError: Boolean?
    var allowRedirects: Boolean?
    var allowRedirectDowngrade: Boolean?
    var maxRedirects: Number?
    var maxSockets: Number?
    var keepAlive: Boolean?
    var deserializeDates: Boolean?
    var allowRetries: Boolean?
    var maxRetries: Number?
}
