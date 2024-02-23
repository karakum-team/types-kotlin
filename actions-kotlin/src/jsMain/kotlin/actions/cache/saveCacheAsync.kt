// Automatically generated - do not modify!

@file:JsModule("@actions/cache")

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

@JsName("saveCache")
external fun saveCacheAsync(paths: ReadonlyArray<String>,
key: String,
options: UploadOptions = definedExternally,
enableCrossOsArchive: Boolean = definedExternally): Promise<Number>

@JsName("saveCache")
external fun saveCacheAsync(cacheId: Number,
archivePath: String,
urls: ReadonlyArray<String>,
uploadId: String): Promise<Void>
