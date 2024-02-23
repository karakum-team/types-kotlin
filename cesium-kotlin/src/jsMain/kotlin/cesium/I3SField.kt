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
 * This class implements an I3S Field which is custom data attached
 * to nodes
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/I3SField.html">Online Documentation</a>
 */
external  class I3SField ()  {
/**
 * Gets the resource for the fields
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/I3SField.html#resource">Online Documentation</a>
 */
val resource: Resource

/**
 * Gets the header for this field.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/I3SField.html#header">Online Documentation</a>
 */
val header: Any

/**
 * Gets the values for this field.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/I3SField.html#values">Online Documentation</a>
 */
val values: Any

/**
 * Gets the name for the field.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/I3SField.html#name">Online Documentation</a>
 */
val name: String

/**
 * Loads the content.
 * @return A promise that is resolved when the field data is loaded
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/I3SField.html#load">Online Documentation</a>
 */
 fun load (): Promise<Void>
}
