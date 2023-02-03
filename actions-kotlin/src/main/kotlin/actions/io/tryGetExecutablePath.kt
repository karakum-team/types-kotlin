package actions.io

import js.core.ReadonlyArray
import kotlin.js.Promise

external fun tryGetExecutablePath(
    filePath: String,
    extensions: ReadonlyArray<String>,
): Promise<string>
