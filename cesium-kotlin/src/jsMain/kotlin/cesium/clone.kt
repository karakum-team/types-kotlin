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
 * Clones an object, returning a new object containing the same properties.
 * @param [object] The object to clone.
 * @param [deep] If true, all properties will be deep cloned recursively.
 *   Default value - `false`
 * @return The cloned object.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#clone">Online Documentation</a>
 */
external  fun clone (
 obj: Any,
 deep: Boolean? = definedExternally
): Any