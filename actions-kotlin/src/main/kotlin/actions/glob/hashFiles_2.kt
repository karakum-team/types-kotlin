package actions.glob

import kotlin.js.Promise

external fun hashFiles(
    patterns: String,
    currentWorkspace: String = definedExternally,
    options: HashFileOptions = definedExternally,
    verbose: Boolean = definedExternally,
): Promise<String>
