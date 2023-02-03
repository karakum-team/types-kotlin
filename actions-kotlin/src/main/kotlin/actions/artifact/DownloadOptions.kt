package actions.artifact

external interface DownloadOptions {
    /**
     * Specifies if a folder is created for the artifact that is downloaded (contents downloaded into this folder),
     * defaults to false if not specified
     * */
    var createArtifactFolder: Boolean?
}
