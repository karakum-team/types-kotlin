package actions.glob

import js.core.ReadonlyArray

external fun getSearchPaths(patterns: ReadonlyArray<Pattern>): ReadonlyArray<String>
