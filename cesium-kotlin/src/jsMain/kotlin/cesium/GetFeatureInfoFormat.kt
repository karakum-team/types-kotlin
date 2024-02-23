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
 * Describes the format in which to request GetFeatureInfo from a Web Map Service (WMS) server.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/GetFeatureInfoFormat.html">Online Documentation</a>
 * 
 * @constructor
 * @param [type] The type of response to expect from a GetFeatureInfo request.  Valid
 *   values are 'json', 'xml', 'html', or 'text'.
 * @param [format] The info format to request from the WMS server.  This is usually a
 *   MIME type such as 'application/json' or text/xml'.  If this parameter is not specified, the provider will request 'json'
 *   using 'application/json', 'xml' using 'text/xml', 'html' using 'text/html', and 'text' using 'text/plain'.
 * @param [callback] A function to invoke with the GetFeatureInfo response from the WMS server
 *   in order to produce an array of picked [ImageryLayerFeatureInfo] instances.  If this parameter is not specified,
 *   a default function for the type of response is used.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/GetFeatureInfoFormat.html">Online Documentation</a>
 */
external  class GetFeatureInfoFormat (
 type: String,
 format: String? = definedExternally,
 callback: Function<*>? = definedExternally
)  {

}
