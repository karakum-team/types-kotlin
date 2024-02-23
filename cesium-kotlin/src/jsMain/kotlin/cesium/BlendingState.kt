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
 * The blending state combines [BlendEquation] and [BlendFunction] and the
 * `enabled` flag to define the full blending state for combining source and
 * destination fragments when rendering.
 * 
 * This is a helper when using custom render states with [Appearance.renderState].
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/BlendingState.html">Online Documentation</a>
 */
external  object BlendingState   {
/**
 * Blending is disabled.
 */
val DISABLED: Any

/**
 * Blending is enabled using alpha blending, `source(source.alpha) + destination(1 - source.alpha)`.
 */
val ALPHA_BLEND: Any

/**
 * Blending is enabled using alpha blending with premultiplied alpha, `source + destination(1 - source.alpha)`.
 */
val PRE_MULTIPLIED_ALPHA_BLEND: Any

/**
 * Blending is enabled using additive blending, `source(source.alpha) + destination`.
 */
val ADDITIVE_BLEND: Any
}
