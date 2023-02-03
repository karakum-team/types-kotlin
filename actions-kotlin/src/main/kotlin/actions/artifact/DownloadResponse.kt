package actions.artifact

external interface DownloadResponse {
    /**
     * The name of the artifact that was downloaded
     */
    var artifactName: String

    /**
     * The full Path to where the artifact was downloaded
     */
    var downloadPath: String
}
