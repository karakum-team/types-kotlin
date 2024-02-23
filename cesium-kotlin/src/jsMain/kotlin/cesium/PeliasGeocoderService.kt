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
 * Provides geocoding via a [Pelias](https://pelias.io/) server.
 * ```
 * // Configure a Viewer to use the Pelias server hosted by https://geocode.earth/
 * const viewer = new Viewer('cesiumContainer', {
 *   geocoder: new PeliasGeocoderService(new Resource({
 *     url: 'https://api.geocode.earth/v1/',
 *       queryParameters: {
 *         api_key: '<Your geocode.earth API key>'
 *     }
 *   }))
 * });
 * ```
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PeliasGeocoderService.html">Online Documentation</a>
 * 
 * @constructor
 * @property [url] The endpoint to the Pelias server.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PeliasGeocoderService.html">Online Documentation</a>
 */
external  class PeliasGeocoderService ( val url: Resource)  {
constructor( url: String)

/**
 * Gets the credit to display after a geocode is performed. Typically this is used to credit
 * the geocoder service.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PeliasGeocoderService.html#credit">Online Documentation</a>
 */
val credit: Credit?

/**
 * @param [query] The query to be sent to the geocoder service
 * @param [type] The type of geocode to perform.
 *   Default value - [GeocodeType.SEARCH]
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PeliasGeocoderService.html#geocode">Online Documentation</a>
 */
 fun geocode (
 query: String,
 type: GeocodeType? = definedExternally
): Promise<ReadonlyArray<GeocoderService.Result>>
}
