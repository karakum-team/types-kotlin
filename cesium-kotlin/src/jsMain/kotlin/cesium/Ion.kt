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
 * Default settings for accessing the Cesium ion API.
 * 
 * An ion access token is only required if you are using any ion related APIs.
 * A default access token is provided for evaluation purposes only.
 * Sign up for a free ion account and get your own access token at [https://cesium.com]
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Ion.html">Online Documentation</a>
 */
external  object Ion   {
/**
 * Gets or sets the default Cesium ion access token.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Ion.html#.defaultAccessToken">Online Documentation</a>
 */
var defaultAccessToken: String

/**
 * Gets or sets the default Cesium ion server.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Ion.html#.defaultServer">Online Documentation</a>
 */
var defaultServer: Resource
}
