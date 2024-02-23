// Automatically generated - do not modify!

package cesium

import js.promise.Promise
import web.canvas.ImageData
import web.dom.Document
import web.dom.Element
import web.html.HTMLCanvasElement
import web.html.HTMLElement
import web.html.HTMLIFrameElement
import web.html.HTMLImageElement
import web.html.HTMLVideoElement
import web.xml.XMLDocument
import js.buffer.ArrayBuffer
import js.objects.jso
import js.array.ReadonlyArray
import js.objects.ReadonlyRecord
import js.core.Void
import js.errors.JsError
import js.typedarrays.Float32Array
import js.typedarrays.Float64Array
import js.typedarrays.Uint16Array
import js.typedarrays.Uint8Array
import web.blob.Blob

/**
 * A function used to compare two items while performing a merge sort.
 * ```
 * function compareNumbers(a, b, userDefinedObject) {
 *     return a - b;
 * }
 * ```
 * @param [a] An item in the array.
 * @param [b] An item in the array.
 * @param [userDefinedObject] An object that was passed to [mergeSort].
 */
typealias mergeSortComparator = ( a: Any,   b: Any,   userDefinedObject: Any?) -> Double