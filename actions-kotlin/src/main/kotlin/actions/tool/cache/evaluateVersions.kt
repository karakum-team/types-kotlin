package actions.tool.cache

import js.core.ReadonlyArray

external fun evaluateVersions(
    versions: ReadonlyArray<String>,
    versionSpec: String,
): String
