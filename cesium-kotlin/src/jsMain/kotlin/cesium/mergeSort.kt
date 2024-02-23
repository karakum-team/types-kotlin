// Automatically generated - do not modify!

@file:JsModule("cesium")

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
 * A stable merge sort.
 * ```
 * // Assume array contains BoundingSpheres in world coordinates.
 * // Sort them in ascending order of distance from the camera.
 * const position = camera.positionWC;
 * mergeSort(array, function(a, b, position) {
 *     return BoundingSphere.distanceSquaredTo(b, position) - BoundingSphere.distanceSquaredTo(a, position);
 * }, position);
 * ```
 * @param [array] The array to sort.
 * @param [comparator] The function to use to compare elements in the array.
 * @param [userDefinedObject] Any item to pass as the third parameter to `comparator`.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#mergeSort">Online Documentation</a>
 */
external  fun mergeSort (
 array: ReadonlyArray<Any>,
 comparator: mergeSortComparator,
 userDefinedObject: Any? = definedExternally
)