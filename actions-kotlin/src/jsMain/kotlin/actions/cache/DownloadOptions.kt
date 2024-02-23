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

sealed external interface DownloadOptions {
    /**
     * Indicates whether to use the Azure Blob SDK to download caches
     * that are stored on Azure Blob Storage to improve reliability and
     * performance
     *
     * @default true
     */
    var useAzureSdk: Boolean?
    /**
     * Number of parallel downloads (this option only applies when using
     * the Azure SDK)
     *
     * @default 8
     */
    var downloadConcurrency: Number?
    /**
     * Indicates whether to use Actions HttpClient with concurrency
     * for Azure Blob Storage
     */
    var concurrentBlobDownloads: Boolean?
    /**
     * Maximum time for each download request, in milliseconds (this
     * option only applies when using the Azure SDK)
     *
     * @default 30000
     */
    var timeoutInMs: Number?
    /**
     * Time after which a segment download should be aborted if stuck
     *
     * @default 3600000
     */
    var segmentTimeoutInMs: Number?
    /**
     * Weather to skip downloading the cache entry.
     * If lookupOnly is set to true, the restore function will only check if
     * a matching cache entry exists and return the cache key if it does.
     *
     * @default false
     */
    var lookupOnly: Boolean?
}
