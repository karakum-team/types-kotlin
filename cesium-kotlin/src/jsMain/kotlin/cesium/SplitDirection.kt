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
 * The direction to display a primitive or ImageryLayer relative to the [Scene.splitPosition].
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#SplitDirection">Online Documentation</a>
 */
sealed external interface SplitDirection {
companion object {

/**
 * Display the primitive or ImageryLayer to the left of the [Scene.splitPosition].
 */
val LEFT: SplitDirection

/**
 * Always display the primitive or ImageryLayer.
 */
val NONE: SplitDirection

/**
 * Display the primitive or ImageryLayer to the right of the [Scene.splitPosition].
 */
val RIGHT: SplitDirection
}
}