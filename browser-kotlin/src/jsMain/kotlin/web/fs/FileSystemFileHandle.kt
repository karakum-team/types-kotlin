// Automatically generated - do not modify!

package web.fs

import js.promise.Promise
import web.file.File

/**
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/FileSystemFileHandle)
 */
sealed external class FileSystemFileHandle :
    FileSystemHandle {
    override val kind: FileSystemHandleKind.file

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/FileSystemFileHandle/createSyncAccessHandle)
     */
    @JsName("createSyncAccessHandle")
    fun createSyncAccessHandleAsync(): Promise<FileSystemSyncAccessHandle>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/FileSystemFileHandle/createWritable)
     */
    @JsName("createWritable")
    fun createWritableAsync(options: FileSystemCreateWritableOptions = definedExternally): Promise<FileSystemWritableFileStream>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/FileSystemFileHandle/getFile)
     */
    @JsName("getFile")
    fun getFileAsync(): Promise<File>
}
