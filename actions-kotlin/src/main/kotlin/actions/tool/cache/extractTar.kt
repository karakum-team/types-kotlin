package actions.tool.cache

import js.core.ReadonlyArray
import kotlin.js.Promise

external fun extractTar(
    file: String,
    dest: String = definedExternally,
    flags: String = definedExternally,
): Promise<String>

external fun extractTar(
    file: String,
    dest: String = definedExternally,
    flags: ReadonlyArray<String> = definedExternally,
): Promise<String>
