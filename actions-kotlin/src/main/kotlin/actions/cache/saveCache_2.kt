package actions.cache

import js.core.Void
import kotlin.js.Promise

external fun saveCache(
    cacheId: Number,
    archivePath: String,
    options: UploadOptions = definedExternally,
): Promise<Void>
