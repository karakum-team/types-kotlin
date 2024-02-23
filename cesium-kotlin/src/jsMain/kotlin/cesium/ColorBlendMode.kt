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
 * Defines different modes for blending between a target color and a primitive's source color.
 * 
 * HIGHLIGHT multiplies the source color by the target color
 * REPLACE replaces the source color with the target color
 * MIX blends the source color and target color together
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#ColorBlendMode">Online Documentation</a>
 */
sealed external interface ColorBlendMode {
companion object {

val HIGHLIGHT: ColorBlendMode

val REPLACE: ColorBlendMode

val MIX: ColorBlendMode
}
}