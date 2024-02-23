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
 * Determines how input texture to a [PostProcessStage] is sampled.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#PostProcessStageSampleMode">Online Documentation</a>
 */
sealed external interface PostProcessStageSampleMode {
companion object {

/**
 * Samples the texture by returning the closest texel.
 */
val NEAREST: PostProcessStageSampleMode

/**
 * Samples the texture through bi-linear interpolation of the four nearest texels.
 */
val LINEAR: PostProcessStageSampleMode
}
}