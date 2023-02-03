package actions.io

import js.core.Void
import kotlin.js.Promise

external fun mkdirP(fsPath: String): Promise<Void>
