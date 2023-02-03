package actions.cache

import kotlin.js.Promise

external fun extractTar(
    archivePath: String,
    compressionMethod: CompressionMethod,
): Promise<void>
