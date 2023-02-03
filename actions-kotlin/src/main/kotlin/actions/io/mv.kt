package actions.io

import kotlin.js.Promise

external fun mv(
    source: String,
    dest: String,
    options: MoveOptions = definedExternally,
): Promise<void>
