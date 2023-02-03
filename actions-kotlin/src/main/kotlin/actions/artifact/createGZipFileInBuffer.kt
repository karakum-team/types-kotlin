package actions.artifact

import node.buffer.Buffer
import kotlin.js.Promise

external fun createGZipFileInBuffer(originalFilePath: String): Promise<Buffer>
