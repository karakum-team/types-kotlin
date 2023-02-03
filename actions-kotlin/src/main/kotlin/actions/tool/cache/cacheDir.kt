package actions.tool.cache

import kotlin.js.Promise

external fun cacheDir(
    sourceDir: String,
    tool: String,
    version: String,
    arch: String = definedExternally,
): Promise<String>
