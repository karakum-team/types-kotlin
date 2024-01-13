// Automatically generated - do not modify!

@file:JsModule("typescript")

package typescript

import js.array.ReadonlyArray

external object JsTyping {
    interface TypingResolutionHost {
        fun directoryExists(path: String): Boolean
        fun fileExists(fileName: String): Boolean
        fun readFile(
            path: String,
            encoding: String = definedExternally,
        ): String?

        fun readDirectory(
            rootDir: String,
            extensions: ReadonlyArray<String>,
            excludes: ReadonlyArray<String>?,
            includes: ReadonlyArray<String>?,
            depth: Int = definedExternally,
        ): ReadonlyArray<String>

        var
    }:
}
}
