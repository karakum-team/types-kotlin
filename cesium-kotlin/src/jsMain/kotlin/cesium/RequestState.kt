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
 * State of the request.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#RequestState">Online Documentation</a>
 */
sealed external interface RequestState {
companion object {

/**
 * Initial unissued state.
 */
val UNISSUED: RequestState

/**
 * Issued but not yet active. Will become active when open slots are available.
 */
val ISSUED: RequestState

/**
 * Actual http request has been sent.
 */
val ACTIVE: RequestState

/**
 * Request completed successfully.
 */
val RECEIVED: RequestState

/**
 * Request was cancelled, either explicitly or automatically because of low priority.
 */
val CANCELLED: RequestState

/**
 * Request failed.
 */
val FAILED: RequestState
}
}