package actions.artifact

import js.core.Void
import kotlin.js.Promise

external fun rmFile(filePath: String): Promise<Void>
