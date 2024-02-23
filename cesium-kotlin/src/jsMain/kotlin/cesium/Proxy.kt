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
 * Base class for proxying requested made by [Resource].
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Proxy.html">Online Documentation</a>
 */
external  class Proxy ()  {
/**
 * Get the final URL to use to request a given resource.
 * @param [resource] The resource to request.
 * @return proxied resource
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Proxy.html#getURL">Online Documentation</a>
 */
 fun getURL ( resource: String): String
}
