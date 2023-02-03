package actions.io

import kotlin.js.Promise

external fun exists(fsPath: String): Promise<boolean>
