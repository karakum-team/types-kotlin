package actions.artifact

import kotlin.js.Promise

external fun rmFile(filePath: String): Promise<void>
