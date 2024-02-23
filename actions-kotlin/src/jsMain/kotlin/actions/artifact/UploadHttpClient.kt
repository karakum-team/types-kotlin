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

external class UploadHttpClient {
constructor()
/**
 * Creates a file container for the new artifact in the remote blob storage/file service
 * @param {string} artifactName Name of the artifact being created
 * @returns The response from the Artifact Service if the file container was successfully created
 */
fun createArtifactInFileContainer(artifactName: String,
options: UploadOptions? = definedExternally): Promise<ArtifactResponse>
/**
 * Concurrently upload all of the files in chunks
 * @param {string} uploadUrl Base Url for the artifact that was created
 * @param {SearchResult[]} filesToUpload A list of information about the files being uploaded
 * @returns The size of all the files uploaded in bytes
 */
fun uploadArtifactToFileContainer(uploadUrl: String,
filesToUpload: ReadonlyArray<UploadSpecification>,
options: UploadOptions = definedExternally): Promise<UploadResults>
/**
 * Updates the size of the artifact from -1 which was initially set when the container was first created for the artifact.
 * Updating the size indicates that we are done uploading all the contents of the artifact
 */
fun patchArtifactSize(size: Number,
artifactName: String): Promise<Void>
}
