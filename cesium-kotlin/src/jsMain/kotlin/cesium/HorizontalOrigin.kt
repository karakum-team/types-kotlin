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
 * The horizontal location of an origin relative to an object, e.g., a [Billboard]
 * or [Label].  For example, setting the horizontal origin to `LEFT`
 * or `RIGHT` will display a billboard to the left or right (in screen space)
 * of the anchor position.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#HorizontalOrigin">Online Documentation</a>
 */
sealed external interface HorizontalOrigin {
companion object {

/**
 * The origin is at the horizontal center of the object.
 */
val CENTER: HorizontalOrigin

/**
 * The origin is on the left side of the object.
 */
val LEFT: HorizontalOrigin

/**
 * The origin is on the right side of the object.
 */
val RIGHT: HorizontalOrigin
}
}