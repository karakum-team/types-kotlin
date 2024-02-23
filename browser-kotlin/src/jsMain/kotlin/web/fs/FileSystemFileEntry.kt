// Automatically generated - do not modify!

package web.fs

import web.file.File
import web.fs.FileSystemEntry

/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/FileSystemFileEntry)
 */
sealed external class FileSystemFileEntry :
FileSystemEntry {
/**
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/FileSystemFileEntry/file)
 */
 fun file(
successCallback: FileCallback,
errorCallback: ErrorCallback = definedExternally,
)
}
