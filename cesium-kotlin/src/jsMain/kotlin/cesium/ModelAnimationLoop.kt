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
 * Determines if and how a glTF animation is looped.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#ModelAnimationLoop">Online Documentation</a>
 */
sealed external interface ModelAnimationLoop {
companion object {

/**
 * Play the animation once; do not loop it.
 */
val NONE: ModelAnimationLoop

/**
 * Loop the animation playing it from the start immediately after it stops.
 */
val REPEAT: ModelAnimationLoop

/**
 * Loop the animation.  First, playing it forward, then in reverse, then forward, and so on.
 */
val MIRRORED_REPEAT: ModelAnimationLoop
}
}