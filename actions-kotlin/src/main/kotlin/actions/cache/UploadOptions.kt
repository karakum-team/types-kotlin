// Automatically generated - do not modify!

package actions.cache

external interface UploadOptions {
    /**
     * Number of parallel cache upload
     *
     * @default 4
     */
    var uploadConcurrency: Number?

    /**
     * Maximum chunk size in bytes for cache upload
     *
     * @default 32MB
     */
    var uploadChunkSize: Number?
}
