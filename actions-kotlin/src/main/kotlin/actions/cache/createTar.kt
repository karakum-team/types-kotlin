package actions.cache

import js.core.ReadonlyArray
import kotlin.js.Promise

external fun createTar(
    archiveFolder: String,
    sourceDirectories: ReadonlyArray<String>,
    compressionMethod: CompressionMethod,
): Promise<void>
