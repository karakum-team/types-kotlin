// Automatically generated - do not modify!

@file:JsModule("@actions/artifact")

package actions.artifact

import js.core.ReadonlyArray
import kotlin.js.Promise

external class DefaultArtifactClient : ArtifactClient {
    /**
     * Constructs a DefaultArtifactClient
     */
    fun /* static */ create(): DefaultArtifactClient

    /**
     * Uploads an artifact
     */
    fun uploadArtifact(
        name: String,
        files: ReadonlyArray<String>,
        rootDirectory: String,
        options: UploadOptions? = definedExternally,
    ): Promise<UploadResponse>

    fun downloadArtifact(
        name: String,
        path: String? = definedExternally,
        options: DownloadOptions? = definedExternally,
    ): Promise<DownloadResponse>

    fun downloadAllArtifacts(path: String? = definedExternally): Promise<ReadonlyArray<DownloadResponse>>
}
