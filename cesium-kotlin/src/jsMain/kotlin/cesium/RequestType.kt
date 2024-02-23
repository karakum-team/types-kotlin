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
 * An enum identifying the type of request. Used for finer grained logging and priority sorting.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#RequestType">Online Documentation</a>
 */
sealed external interface RequestType {
companion object {

/**
 * Terrain request.
 */
val TERRAIN: RequestType

/**
 * Imagery request.
 */
val IMAGERY: RequestType

/**
 * 3D Tiles request.
 */
val TILES3D: RequestType

/**
 * Other request.
 */
val OTHER: RequestType
}
}