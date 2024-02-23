// Automatically generated - do not modify!

@file:JsModule("cesium")

@file:Suppress(
"EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER",
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
 * An event that is raised when a request encounters an error.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/RequestErrorEvent.html">Online Documentation</a>
 * 
 * @constructor
 * @property [statusCode] The HTTP error status code, such as 404.
 * @property [response] The response included along with the error.
 * @param [responseHeaders] The response headers, represented either as an object literal or as a
 *   string in the format returned by XMLHttpRequest's getAllResponseHeaders() function.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/RequestErrorEvent.html">Online Documentation</a>
 */
external  class RequestErrorEvent (
 var statusCode: Int = definedExternally,
 var response: Any = definedExternally,
 responseHeaders: dynamic = definedExternally
)  {
/**
 * The headers included in the response, represented as an object literal of key/value pairs.
 * If the error does not include any headers, this property will be undefined.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/RequestErrorEvent.html#responseHeaders">Online Documentation</a>
 */
var responseHeaders: Any
}
