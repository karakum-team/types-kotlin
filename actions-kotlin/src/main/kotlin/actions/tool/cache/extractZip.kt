package actions.tool.cache

import kotlin.js.Promise

external fun extractZip(
    file: String,
    dest: String = definedExternally,
): Promise<string>
