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

suspend fun restoreCache(
paths: ReadonlyArray<String>,
primaryKey: String,
) : String? =
 restoreCacheAsync(
paths = paths,
primaryKey = primaryKey,
).await()

suspend fun restoreCache(
paths: ReadonlyArray<String>,
primaryKey: String,
restoreKeys: ReadonlyArray<String>,
) : String? =
 restoreCacheAsync(
paths = paths,
primaryKey = primaryKey,
restoreKeys = restoreKeys,
).await()

suspend fun restoreCache(
paths: ReadonlyArray<String>,
primaryKey: String,
restoreKeys: ReadonlyArray<String>,
options: DownloadOptions,
) : String? =
 restoreCacheAsync(
paths = paths,
primaryKey = primaryKey,
restoreKeys = restoreKeys,
options = options,
).await()

suspend fun restoreCache(
paths: ReadonlyArray<String>,
primaryKey: String,
restoreKeys: ReadonlyArray<String>,
options: DownloadOptions,
enableCrossOsArchive: Boolean,
) : String? =
 restoreCacheAsync(
paths = paths,
primaryKey = primaryKey,
restoreKeys = restoreKeys,
options = options,
enableCrossOsArchive = enableCrossOsArchive,
).await()
