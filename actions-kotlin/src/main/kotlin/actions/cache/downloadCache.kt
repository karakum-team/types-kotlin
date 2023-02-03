package actions.cache

import kotlin.js.Promise

external fun downloadCache(
    archiveLocation: String,
    archivePath: String,
    options: DownloadOptions = definedExternally,
): Promise<void>
