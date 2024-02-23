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
 * A light source. This type describes an interface and is not intended to be instantiated directly. Together, `color` and `intensity` produce a high-dynamic-range light color. `intensity` can also be used individually to dim or brighten the light without changing the hue.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Light.html">Online Documentation</a>
 */
external abstract  class Light ()  {
/**
 * The color of the light.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Light.html#color">Online Documentation</a>
 */
abstract var color: Color

/**
 * The intensity controls the strength of the light. `intensity` has a minimum value of 0.0 and no maximum value.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Light.html#intensity">Online Documentation</a>
 */
abstract var intensity: Double
}
