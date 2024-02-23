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
 * The lighting model to use for lighting a [Model].
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#LightingModel">Online Documentation</a>
 */
sealed external interface LightingModel {
companion object {

/**
 * Use unlit shading, i.e. skip lighting calculations. The model's
 * diffuse color (assumed to be linear RGB, not sRGB) is used directly
 * when computing `out_FragColor`. The alpha mode is still
 * applied.
 */
val UNLIT: LightingModel

/**
 * Use physically-based rendering lighting calculations. This includes
 * both PBR metallic roughness and PBR specular glossiness. Image-based
 * lighting is also applied when possible.
 */
val PBR: LightingModel
}
}