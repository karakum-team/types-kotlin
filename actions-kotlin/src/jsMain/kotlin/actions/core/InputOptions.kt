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

sealed external interface InputOptions {
    /** Optional. Whether the input is required. If required and not present, will throw. Defaults to false */
    var required: Boolean?
    /** Optional. Whether leading/trailing whitespace will be trimmed for the input. Defaults to true */
    var trimWhitespace: Boolean?
}
