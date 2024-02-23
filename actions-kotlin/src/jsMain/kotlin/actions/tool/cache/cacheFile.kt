// Automatically generated - do not modify!

package actions.tool.cache

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

suspend fun cacheFile(
sourceFile: String,
targetFile: String,
tool: String,
version: String,
) : String =
 cacheFileAsync(
sourceFile = sourceFile,
targetFile = targetFile,
tool = tool,
version = version,
).await()

suspend fun cacheFile(
sourceFile: String,
targetFile: String,
tool: String,
version: String,
arch: String,
) : String =
 cacheFileAsync(
sourceFile = sourceFile,
targetFile = targetFile,
tool = tool,
version = version,
arch = arch,
).await()
