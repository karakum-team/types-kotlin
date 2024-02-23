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

suspend fun <T> retry(
name: String,
method: () -> Promise<T>,
getStatusCode: (arg0: T) -> Number?,
) : T =
 retryAsync(
name = name,
method = method,
getStatusCode = getStatusCode,
).await()

suspend fun <T> retry(
name: String,
method: () -> Promise<T>,
getStatusCode: (arg0: T) -> Number?,
maxAttempts: Number,
) : T =
 retryAsync(
name = name,
method = method,
getStatusCode = getStatusCode,
maxAttempts = maxAttempts,
).await()

suspend fun <T> retry(
name: String,
method: () -> Promise<T>,
getStatusCode: (arg0: T) -> Number?,
maxAttempts: Number,
delay: Number,
) : T =
 retryAsync(
name = name,
method = method,
getStatusCode = getStatusCode,
maxAttempts = maxAttempts,
delay = delay,
).await()

suspend fun <T> retry(
name: String,
method: () -> Promise<T>,
getStatusCode: (arg0: T) -> Number?,
maxAttempts: Number,
delay: Number,
onError: ((arg0: Error) -> T?)?,
) : T =
 retryAsync(
name = name,
method = method,
getStatusCode = getStatusCode,
maxAttempts = maxAttempts,
delay = delay,
onError = onError,
).await()
