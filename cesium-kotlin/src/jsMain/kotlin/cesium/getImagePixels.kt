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
 * Extract a pixel array from a loaded image.  Draws the image
 * into a canvas so it can read the pixels back.
 * @param [image] The image to extract pixels from.
 * @param [width] The width of the image. If not defined, then image.width is assigned.
 * @param [height] The height of the image. If not defined, then image.height is assigned.
 * @return The pixels of the image.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#getImagePixels">Online Documentation</a>
 */
external  fun getImagePixels (
 image: dynamic,
 width: Double,
 height: Double
): ImageData