// Automatically generated - do not modify!

package actions.exec

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

suspend fun exec(
commandLine: String,
) : Number =
 execAsync(
commandLine = commandLine,
).await()

suspend fun exec(
commandLine: String,
args: ReadonlyArray<String>,
) : Number =
 execAsync(
commandLine = commandLine,
args = args,
).await()

suspend fun exec(
commandLine: String,
args: ReadonlyArray<String>,
options: ExecOptions,
) : Number =
 execAsync(
commandLine = commandLine,
args = args,
options = options,
).await()
