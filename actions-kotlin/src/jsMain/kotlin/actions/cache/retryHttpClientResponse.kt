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

suspend fun retryHttpClientResponse(
name: String,
method: () -> Promise<HttpClientResponse>,
) : HttpClientResponse =
 retryHttpClientResponseAsync(
name = name,
method = method,
).await()

suspend fun retryHttpClientResponse(
name: String,
method: () -> Promise<HttpClientResponse>,
maxAttempts: Number,
) : HttpClientResponse =
 retryHttpClientResponseAsync(
name = name,
method = method,
maxAttempts = maxAttempts,
).await()

suspend fun retryHttpClientResponse(
name: String,
method: () -> Promise<HttpClientResponse>,
maxAttempts: Number,
delay: Number,
) : HttpClientResponse =
 retryHttpClientResponseAsync(
name = name,
method = method,
maxAttempts = maxAttempts,
delay = delay,
).await()
