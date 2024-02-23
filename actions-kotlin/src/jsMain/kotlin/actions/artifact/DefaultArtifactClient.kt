// Automatically generated - do not modify!

@file:JsModule("@actions/artifact")

package actions.artifact

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

sealed external class DefaultArtifactClient : ArtifactClient {
/**
 * Uploads an artifact
 */
fun uploadArtifact(name: String,
files: ReadonlyArray<String>,
rootDirectory: String,
options: UploadOptions? = definedExternally): Promise<UploadResponse>
fun downloadArtifact(name: String,
path: String? = definedExternally,
options: DownloadOptions? = definedExternally): Promise<DownloadResponse>
fun downloadAllArtifacts(path: String? = definedExternally): Promise<ReadonlyArray<DownloadResponse>>
companion object {
/**
 * Constructs a DefaultArtifactClient
 */
fun create(): DefaultArtifactClient
}
}
