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
 * Determines how opaque and translucent parts of billboards, points, and labels are blended with the scene.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#BlendOption">Online Documentation</a>
 */
sealed external interface BlendOption {
companion object {

/**
 * The billboards, points, or labels in the collection are completely opaque.
 */
val OPAQUE: BlendOption

/**
 * The billboards, points, or labels in the collection are completely translucent.
 */
val TRANSLUCENT: BlendOption

/**
 * The billboards, points, or labels in the collection are both opaque and translucent.
 */
val OPAQUE_AND_TRANSLUCENT: BlendOption
}
}