package actions.cache

import js.core.Void
import kotlin.js.Promise

external fun downloadCacheHttpClient(
    archiveLocation: String,
    archivePath: String,
): Promise<Void>
