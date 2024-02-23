// Automatically generated - do not modify!

@file:JsModule("cesium")

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

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
 * The data type of a pixel.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#PixelDatatype">Online Documentation</a>
 */
sealed external interface PixelDatatype {
companion object {

val UNSIGNED_BYTE: PixelDatatype

val UNSIGNED_SHORT: PixelDatatype

val UNSIGNED_INT: PixelDatatype

val FLOAT: PixelDatatype

val HALF_FLOAT: PixelDatatype

val UNSIGNED_INT_24_8: PixelDatatype

val UNSIGNED_SHORT_4_4_4_4: PixelDatatype

val UNSIGNED_SHORT_5_5_5_1: PixelDatatype

val UNSIGNED_SHORT_5_6_5: PixelDatatype
}
}