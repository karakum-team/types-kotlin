package actions.io

import kotlin.js.Promise

external fun rmRF(inputPath: String): Promise<void>
