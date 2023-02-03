package actions.cache

external interface DownloadOptions {
    /**
     * Indicates whether to use the Azure Blob SDK to download caches
     * that are stored on Azure Blob Storage to improve reliability and
     * performance
     *
     * @default true
     */
    // useAzureSdk?: boolean
    /**
     * Number of parallel downloads (this option only applies when using
     * the Azure SDK)
     *
     * @default 8
     */
    // downloadConcurrency?: number
    /**
     * Maximum time for each download request, in milliseconds (this
     * option only applies when using the Azure SDK)
     *
     * @default 30000
     */
    // timeoutInMs?: number
    /**
     * Time after which a segment download should be aborted if stuck
     *
     * @default 3600000
     */
    // segmentTimeoutInMs?: number
}