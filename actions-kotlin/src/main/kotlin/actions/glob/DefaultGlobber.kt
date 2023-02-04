// Automatically generated - do not modify!

@file:JsModule("@actions/glob")

package actions.glob

import js.core.ReadonlyArray
import kotlin.js.Promise

external class DefaultGlobber : Globber {
    // private readonly options
    // private readonly patterns
    // private readonly searchPaths
    // private constructor()
    fun getSearchPaths(): ReadonlyArray<String>
    fun glob(): Promise<ReadonlyArray<String>>
    fun globGenerator(): Any /* AsyncGenerator<string, void> */

    /**
     * Constructs a DefaultGlobber
     */
    fun /* static */ create(
        patterns: String,
        options: GlobOptions = definedExternally,
    ): Promise<DefaultGlobber>
    // private static stat
}
