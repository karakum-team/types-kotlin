// Automatically generated - do not modify!

@file:JsModule("cesium")

@file:Suppress(
"EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER",
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
 * A ParticleEmitter that emits particles from a circle.
 * Particles will be positioned within a circle and have initial velocities going along the z vector.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/CircleEmitter.html">Online Documentation</a>
 * 
 * @constructor
 * @property [radius] The radius of the circle in meters.
 *   Default value - `1.0`
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/CircleEmitter.html">Online Documentation</a>
 */
external  class CircleEmitter ( var radius: Double = definedExternally)  {
/**
 * The angle of the cone in radians.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/CircleEmitter.html#angle">Online Documentation</a>
 */
var angle: Double
}
