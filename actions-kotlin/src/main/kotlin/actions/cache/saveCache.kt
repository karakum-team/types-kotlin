// Automatically generated - do not modify!

@file:JsModule("@actions/cache")

package actions.cache

import js.core.ReadonlyArray
import kotlin.js.Promise

external fun saveCache(
    paths: ReadonlyArray<String>,
    key: String,
    options: UploadOptions = definedExternally,
    enableCrossOsArchive: Boolean = definedExternally,
): Promise<Number>
