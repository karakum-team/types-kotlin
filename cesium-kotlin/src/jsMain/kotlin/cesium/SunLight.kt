// Automatically generated - do not modify!

@file:JsModule("cesium")

@file:Suppress(
"NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
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
 * A directional light source that originates from the Sun.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/SunLight.html">Online Documentation</a>
 */
external  class SunLight ()  : Light {
/**
 * The color of the light.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/SunLight.html#color">Online Documentation</a>
 */
override var color: Color

/**
 * The intensity of the light.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/SunLight.html#intensity">Online Documentation</a>
 */
override var intensity: Double
}
inline fun SunLight(
    block: SunLight.() -> Unit
): SunLight =
    SunLight().apply(block)