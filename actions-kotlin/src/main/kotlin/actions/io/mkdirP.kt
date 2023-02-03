package actions.io

import kotlin.js.Promise

external fun mkdirP(fsPath: String): Promise<void>
