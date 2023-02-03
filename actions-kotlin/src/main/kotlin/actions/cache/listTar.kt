package actions.cache

import kotlin.js.Promise

external fun listTar(
    archivePath: String,
    compressionMethod: CompressionMethod,
): Promise<void>
