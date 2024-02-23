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
 * A simple proxy that appends the desired resource as the sole query parameter
 * to the given proxy URL.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/DefaultProxy.html">Online Documentation</a>
 * 
 * @constructor
 * @param [proxy] The proxy URL that will be used to requests all resources.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/DefaultProxy.html">Online Documentation</a>
 */
external  class DefaultProxy ( proxy: String)  {
/**
 * Get the final URL to use to request a given resource.
 * @param [resource] The resource to request.
 * @return proxied resource
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/DefaultProxy.html#getURL">Online Documentation</a>
 */
 fun getURL ( resource: String): String
}
