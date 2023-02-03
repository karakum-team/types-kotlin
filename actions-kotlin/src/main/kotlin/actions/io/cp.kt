package actions.io

import kotlin.js.Promise

external fun cp(
    source: String,
    dest: String,
    options: CopyOptions = definedExternally,
): Promise<void>
