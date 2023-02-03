package actions.glob

import kotlin.js.Promise

external fun create(
    patterns: String,
    options: GlobOptions = definedExternally,
): Promise<Globber>
