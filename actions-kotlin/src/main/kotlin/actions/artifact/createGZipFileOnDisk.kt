package actions.artifact

import kotlin.js.Promise

external fun createGZipFileOnDisk(
    originalFilePath: String,
    tempFilePath: String,
): Promise<number>
