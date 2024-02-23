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
 * Subdivides an array into a number of smaller, equal sized arrays.
 * @param [array] The array to divide.
 * @param [numberOfArrays] The number of arrays to divide the provided array into.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#subdivideArray">Online Documentation</a>
 */
external  fun subdivideArray (
 array: ReadonlyArray<Any>,
 numberOfArrays: Int
)