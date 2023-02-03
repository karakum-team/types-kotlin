package actions.glob

import kotlin.js.Promise

external fun hashFiles(
    globber: Globber,
    currentWorkspace: String,
    verbose: Boolean = definedExternally,
): Promise<string>
