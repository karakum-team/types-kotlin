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
 * Converts an object representing a set of name/value pairs into a query string,
 * with names and values encoded properly for use in a URL.  Values that are arrays
 * will produce multiple values with the same name.
 * ```
 * const str = objectToQuery({
 *     key1 : 'some value',
 *     key2 : 'a/b',
 *     key3 : ['x', 'y']
 * });
 * ```
 * @param [obj] The object containing data to encode.
 * @return An encoded query string.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#objectToQuery">Online Documentation</a>
 */
external  fun objectToQuery ( obj: Any): String