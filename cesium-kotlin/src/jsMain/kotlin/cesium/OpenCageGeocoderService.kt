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
 * Provides geocoding via a [OpenCage](https://opencagedata.com/) server.
 * ```
 * // Configure a Viewer to use the OpenCage Geocoder
 * const viewer = new Viewer('cesiumContainer', {
 *   geocoder: new OpenCageGeocoderService('https://api.opencagedata.com/geocode/v1/', '<API key>')
 * });
 * ```
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/OpenCageGeocoderService.html">Online Documentation</a>
 * 
 * @constructor
 * @property [url] The endpoint to the OpenCage server.
 * @param [apiKey] The OpenCage API Key.
 * @param [params] An object with the following properties (See https://opencagedata.com/api#forward-opt):
 * @param [params.abbrv] When set to 1 we attempt to abbreviate and shorten the formatted string we return.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/OpenCageGeocoderService.html">Online Documentation</a>
 */
external  class OpenCageGeocoderService (
 val url: Resource,
 apiKey: String,
 params: dynamic = definedExternally
)  {
constructor(
 url: String,
 apiKey: String,
 params: dynamic = definedExternally
)

/**
 * Optional params passed to OpenCage in order to customize geocoding
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/OpenCageGeocoderService.html#params">Online Documentation</a>
 */
val params: Any

/**
 * Gets the credit to display after a geocode is performed. Typically this is used to credit
 * the geocoder service.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/OpenCageGeocoderService.html#credit">Online Documentation</a>
 */
val credit: Credit?

/**
 * @param [query] The query to be sent to the geocoder service
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/OpenCageGeocoderService.html#geocode">Online Documentation</a>
 */
 fun geocode ( query: String): Promise<ReadonlyArray<GeocoderService.Result>>
}
