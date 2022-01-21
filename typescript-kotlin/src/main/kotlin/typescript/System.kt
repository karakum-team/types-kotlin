// Automatically generated - do not modify!

package typescript

external interface System {
    var args: ReadonlyArray<String>
    var newLine: String
    var useCaseSensitiveFileNames: Boolean
    fun write(s: String)
    val writeOutputIsTTY: (() -> Boolean)?
    val getWidthOfTerminal: (() -> Double)?
    fun readFile(
        path: String,
        encoding: String = definedExternally,
    ): String?

    val getFileSize: ((path: String) -> Double)?
    fun writeFile(
        path: String,
        data: String,
        writeByteOrderMark: Boolean = definedExternally,
    )

    /**
     * @pollingInterval - this parameter is used in polling-based watchers and ignored in watchers that
     * use native OS file watching
     */
    val watchFile: ((
        path: String,
        callback: FileWatcherCallback,
        pollingInterval: Double?,
        options: WatchOptions?,
    ) -> FileWatcher)?
    val watchDirectory: ((
        path: String,
        callback: DirectoryWatcherCallback,
        recursive: Boolean?,
        options: WatchOptions?,
    ) -> FileWatcher)?

    fun resolvePath(path: String): String
    fun fileExists(path: String): Boolean
    fun directoryExists(path: String): Boolean
    fun createDirectory(path: String)
    fun getExecutingFilePath(): String
    fun getCurrentDirectory(): String
    fun getDirectories(path: String): ReadonlyArray<String>
    fun readDirectory(
        path: String,
        extensions: ReadonlyArray<String> = definedExternally,
        exclude: ReadonlyArray<String> = definedExternally,
        include: ReadonlyArray<String> = definedExternally,
        depth: Double = definedExternally,
    ): ReadonlyArray<String>

    val getModifiedTime: ((path: String) -> kotlin.js.Date?)?
    val setModifiedTime: ((
        path: String,
        time: kotlin.js.Date,
    ) -> Unit)?
    val deleteFile: ((path: String) -> Unit)?

    /**
     * A good implementation is node.js' `crypto.createHash`. (https://nodejs.org/api/crypto.html#crypto_crypto_createhash_algorithm)
     */
    val createHash: ((data: String) -> String)?

    /** This must be cryptographically secure. Only implement this method using `crypto.createHash("sha256")`. */
    val createSHA256Hash: ((data: String) -> String)?
    val getMemoryUsage: (() -> Double)?
    fun exit(exitCode: Double = definedExternally)
    val realpath: ((path: String) -> String)?
    val setTimeout: ((
        callback: Function<Unit>,
        ms: Double,
/* vararg */
        args: dynamic,
    ) -> Any)?
    val clearTimeout: ((timeoutId: Any) -> Unit)?
    val clearScreen: (() -> Unit)?
    val base64decode: ((input: String) -> String)?
    val base64encode: ((input: String) -> String)?
}
