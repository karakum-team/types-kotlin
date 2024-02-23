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
 * Geocodes queries containing longitude and latitude coordinates and an optional height.
 * Query format: `longitude latitude (height)` with longitude/latitude in degrees and height in meters.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/CartographicGeocoderService.html">Online Documentation</a>
 */
external  class CartographicGeocoderService ()  {
/**
 * Gets the credit to display after a geocode is performed. Typically this is used to credit
 * the geocoder service.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/CartographicGeocoderService.html#credit">Online Documentation</a>
 */
val credit: Credit?

/**
 * @param [query] The query to be sent to the geocoder service
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/CartographicGeocoderService.html#geocode">Online Documentation</a>
 */
 fun geocode ( query: String): Promise<ReadonlyArray<GeocoderService.Result>>
}
