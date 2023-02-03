package actions.io

import js.core.Void
import kotlin.js.Promise

external fun cp(
    source: String,
    dest: String,
    options: CopyOptions = definedExternally,
): Promise<Void>
