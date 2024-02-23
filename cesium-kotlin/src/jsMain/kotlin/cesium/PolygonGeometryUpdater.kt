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
 * A [GeometryUpdater] for polygons.
 * Clients do not normally create this class directly, but instead rely on [DataSourceDisplay].
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PolygonGeometryUpdater.html">Online Documentation</a>
 * 
 * @constructor
 * @param [entity] The entity containing the geometry to be visualized.
 * @param [scene] The scene where visualization is taking place.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PolygonGeometryUpdater.html">Online Documentation</a>
 */
external  class PolygonGeometryUpdater (
 entity: Entity,
 scene: Scene
)  {
/**
 * Creates the geometry instance which represents the fill of the geometry.
 * @param [time] The time to use when retrieving initial attribute values.
 * @return The geometry instance representing the filled portion of the geometry.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PolygonGeometryUpdater.html#createFillGeometryInstance">Online Documentation</a>
 */
 fun createFillGeometryInstance ( time: JulianDate): GeometryInstance

/**
 * Creates the geometry instance which represents the outline of the geometry.
 * @param [time] The time to use when retrieving initial attribute values.
 * @return The geometry instance representing the outline portion of the geometry.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PolygonGeometryUpdater.html#createOutlineGeometryInstance">Online Documentation</a>
 */
 fun createOutlineGeometryInstance ( time: JulianDate): GeometryInstance
}
