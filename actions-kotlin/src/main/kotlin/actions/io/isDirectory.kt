package actions.io

import kotlin.js.Promise

external fun isDirectory(
    fsPath: String,
    useStat: Boolean = definedExternally,
): Promise<Boolean>
