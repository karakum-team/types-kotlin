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
 * Determines how two pixels' values are combined.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#BlendEquation">Online Documentation</a>
 */
sealed external interface BlendEquation {
companion object {

/**
 * Pixel values are added componentwise.  This is used in additive blending for translucency.
 */
val ADD: BlendEquation

/**
 * Pixel values are subtracted componentwise (source - destination).  This is used in alpha blending for translucency.
 */
val SUBTRACT: BlendEquation

/**
 * Pixel values are subtracted componentwise (destination - source).
 */
val REVERSE_SUBTRACT: BlendEquation

/**
 * Pixel values are given to the minimum function (min(source, destination)).
 * 
 * This equation operates on each pixel color component.
 */
val MIN: BlendEquation

/**
 * Pixel values are given to the maximum function (max(source, destination)).
 * 
 * This equation operates on each pixel color component.
 */
val MAX: BlendEquation
}
}