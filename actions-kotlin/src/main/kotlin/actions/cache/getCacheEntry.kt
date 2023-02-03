package actions.cache

import js.core.ReadonlyArray
import kotlin.js.Promise

external fun getCacheEntry(
    keys: ReadonlyArray<String>,
    paths: ReadonlyArray<String>,
    options: InternalCacheOptions = definedExternally,
): Promise<ArtifactCacheEntry?>
