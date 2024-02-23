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
 * An hierarchy of linear rings which define a polygon and its holes.
 * The holes themselves may also have holes which nest inner polygons.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PolygonHierarchy.html">Online Documentation</a>
 * 
 * @constructor
 * @property [positions] A linear ring defining the outer boundary of the polygon or hole.
 * @property [holes] An array of polygon hierarchies defining holes in the polygon.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PolygonHierarchy.html">Online Documentation</a>
 */
external  class PolygonHierarchy (
 var positions: ReadonlyArray<Cartesian3> = definedExternally,
 var holes: ReadonlyArray<PolygonHierarchy> = definedExternally
)  {

}
