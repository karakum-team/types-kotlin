package actions.tool.cache

import kotlin.js.Promise

external fun extract7z(
    file: String,
    dest: String = definedExternally,
    _7zPath: String = definedExternally,
): Promise<String>
