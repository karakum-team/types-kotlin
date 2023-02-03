package actions.io

import kotlin.js.Promise

external fun which(
    tool: String,
    check: Boolean = definedExternally,
): Promise<string>
