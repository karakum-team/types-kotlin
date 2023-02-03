package actions.cache

import kotlin.js.Promise

external fun downloadCacheHttpClient(
    archiveLocation: String,
    archivePath: String,
): Promise<void>
