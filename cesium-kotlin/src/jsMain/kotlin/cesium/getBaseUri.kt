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
 * Given a URI, returns the base path of the URI.
 * ```
 * // basePath will be "/Gallery/";
 * const basePath = getBaseUri('/Gallery/simple.czml?value=true&example=false');
 * 
 * // basePath will be "/Gallery/?value=true&example=false";
 * const basePath = getBaseUri('/Gallery/simple.czml?value=true&example=false', true);
 * ```
 * @param [uri] The Uri.
 * @param [includeQuery] Whether or not to include the query string and fragment form the uri
 *   Default value - `false`
 * @return The base path of the Uri.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#getBaseUri">Online Documentation</a>
 */
external  fun getBaseUri (
 uri: String,
 includeQuery: Boolean? = definedExternally
): String