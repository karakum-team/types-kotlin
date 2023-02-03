package actions.cache

import js.core.ReadonlyArray
import kotlin.js.Promise

external fun resolvePaths(patterns: ReadonlyArray<String>): Promise<ReadonlyArray<String>>
