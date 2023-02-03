package actions.cache

import kotlin.js.Promise

external fun downloadCacheStorageSDK(
    archiveLocation: String,
    archivePath: String,
    options: DownloadOptions,
): Promise<void>
