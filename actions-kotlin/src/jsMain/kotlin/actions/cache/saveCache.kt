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

suspend fun saveCache(
paths: ReadonlyArray<String>,
key: String,
) : Number =
 saveCacheAsync(
paths = paths,
key = key,
).await()

suspend fun saveCache(
paths: ReadonlyArray<String>,
key: String,
options: UploadOptions,
) : Number =
 saveCacheAsync(
paths = paths,
key = key,
options = options,
).await()

suspend fun saveCache(
paths: ReadonlyArray<String>,
key: String,
options: UploadOptions,
enableCrossOsArchive: Boolean,
) : Number =
 saveCacheAsync(
paths = paths,
key = key,
options = options,
enableCrossOsArchive = enableCrossOsArchive,
).await()

suspend fun saveCache(
cacheId: Number,
archivePath: String,
urls: ReadonlyArray<String>,
uploadId: String,
) {
 saveCacheAsync(
cacheId = cacheId,
archivePath = archivePath,
urls = urls,
uploadId = uploadId,
).await() 
}
