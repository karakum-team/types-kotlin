// Automatically generated - do not modify!

package web.clipboard

import js.core.Void
import js.promise.Promise
import web.events.EventTarget

/**
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Clipboard)
 */
sealed external class Clipboard :
    EventTarget {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Clipboard/read)
     */
    @JsName("read")
    fun readAsync(): Promise<ClipboardItems>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Clipboard/readText)
     */
    @JsName("readText")
    fun readTextAsync(): Promise<String>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Clipboard/write)
     */
    @JsName("write")
    fun writeAsync(data: ClipboardItems): Promise<Void>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/Clipboard/writeText)
     */
    @JsName("writeText")
    fun writeTextAsync(data: String): Promise<Void>
}
