package actions.cache

import kotlin.js.Promise

external fun saveCache(
    cacheId: Number,
    archivePath: String,
    options: UploadOptions = definedExternally,
): Promise<void>
