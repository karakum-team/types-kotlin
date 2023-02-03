package actions.artifact

import kotlin.js.Promise

external fun getFileSize(filePath: String): Promise<Number>
