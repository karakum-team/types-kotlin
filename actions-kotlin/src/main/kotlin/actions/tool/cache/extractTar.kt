package actions.tool.cache

import js.core.ReadonlyArray

external fun extractTar(
    file: String,
    dest: String = definedExternally,
    flags: ReadonlyArray<string | string> = definedExternally): Promise<string>
