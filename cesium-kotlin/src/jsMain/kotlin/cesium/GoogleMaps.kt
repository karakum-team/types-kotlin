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
 * Default settings for accessing the Google Maps API.
 * <br/>
 * An API key is only required if you are directly using any Google Maps APIs, such as through [createGooglePhotorealistic3DTileset].
 * Follow instructions for managing API keys for the Google Maps Platform at [https://developers.google.com/maps/documentation/embed/get-api-key]
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/GoogleMaps.html">Online Documentation</a>
 */
external  object GoogleMaps   {
/**
 * Gets or sets the default Google Maps API key.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/GoogleMaps.html#.defaultApiKey">Online Documentation</a>
 */
var defaultApiKey: String?

/**
 * Gets or sets the default Google Map Tiles API endpoint.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/GoogleMaps.html#.mapTilesApiEndpoint">Online Documentation</a>
 */
var mapTilesApiEndpoint: Resource
}
