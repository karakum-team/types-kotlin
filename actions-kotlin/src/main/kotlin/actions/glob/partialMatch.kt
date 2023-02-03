package actions.glob

import js.core.ReadonlyArray

external fun partialMatch(
    patterns: ReadonlyArray<Pattern>,
    itemPath: String,
): Boolean
