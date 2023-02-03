package actions.artifact

external interface UploadResults {
    /**
     * The size in bytes of data that was transferred during the upload process to the actions backend service. This takes into account possible
     * gzip compression to reduce the amount of data that needs to be transferred
     */
    // uploadSize: number
    /**
     * The raw size of the files that were specified for upload
     */
    // totalSize: number
    /**
     * An array of files that failed to upload
     */
    // failedItems: string[]
}