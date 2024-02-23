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
 * Whether a classification affects terrain, 3D Tiles or both.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#ClassificationType">Online Documentation</a>
 */
sealed external interface ClassificationType {
companion object {

/**
 * Only terrain will be classified.
 */
val TERRAIN: ClassificationType

/**
 * Only 3D Tiles will be classified.
 */
val CESIUM_3D_TILE: ClassificationType

/**
 * Both terrain and 3D Tiles will be classified.
 */
val BOTH: ClassificationType
}
}