// Automatically generated - do not modify!

@file:JsModule("@actions/glob")

package actions.glob

import js.core.ReadonlyArray
import kotlin.js.Promise

external class DefaultGlobber : Globber {
    // private constructor()
    override fun getSearchPaths(): ReadonlyArray<String>
    override fun glob(): Promise<ReadonlyArray<String>>
    override fun globGenerator(): Any /* AsyncGenerator<string, void> */

    /**
     * Constructs a DefaultGlobber
     */
    fun /* static */ create(
        patterns: String,
        options: GlobOptions = definedExternally,
    ): Promise<DefaultGlobber>
}
