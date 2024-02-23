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
 * Given a URI, returns the last segment of the URI, removing any path or query information.
 * ```
 * //fileName will be"simple.czml";
 * const fileName = getFilenameFromUri('/Gallery/simple.czml?value=true&example=false');
 * ```
 * @param [uri] The Uri.
 * @return The last segment of the Uri.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#getFilenameFromUri">Online Documentation</a>
 */
external  fun getFilenameFromUri ( uri: String): String