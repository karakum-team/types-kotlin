// Automatically generated - do not modify!

package web.storage

import js.promise.Promise
import web.fs.FileSystemDirectoryHandle

/**
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/StorageManager)
 */
sealed external class StorageManager {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/StorageManager/estimate)
     */
    @JsName("estimate")
    fun estimateAsync(): Promise<StorageEstimate>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/StorageManager/getDirectory)
     */
    @JsName("getDirectory")
    fun getDirectoryAsync(): Promise<FileSystemDirectoryHandle>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/StorageManager/persist)
     */
    @JsName("persist")
    fun persistAsync(): Promise<Boolean>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/StorageManager/persisted)
     */
    @JsName("persisted")
    fun persistedAsync(): Promise<Boolean>
}
