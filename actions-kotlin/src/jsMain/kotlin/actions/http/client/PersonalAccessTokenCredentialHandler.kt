// Automatically generated - do not modify!

@file:JsModule("@actions/http-client/lib/auth")

@file:Suppress(
"ABSTRACT_MEMBER_NOT_IMPLEMENTED",
)

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

external class PersonalAccessTokenCredentialHandler : RequestHandler {
var token: String
constructor(token: String)
override fun prepareRequest(options: node.http.RequestOptions)
fun canHandleAuthentication(): Boolean
fun handleAuthentication(): Promise<HttpClientResponse>
}
