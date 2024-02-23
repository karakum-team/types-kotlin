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
 * Creates a [CesiumTerrainProvider] instance for the [Cesium World Bathymetry](https://cesium.com/content/#cesium-world-bathymetry).
 * ```
 * // Create Cesium World Bathymetry with default settings
 * try {
 *   const viewer = new Viewer("cesiumContainer", {
 *     terrainProvider: await createWorldBathymetryAsync();
 *   });
 * } catch (error) {
 *   console.log(error);
 * }
 * ```
 * ```
 * // Create Cesium World Bathymetry with normals.
 * try {
 *   const viewer1 = new Viewer("cesiumContainer", {
 *     terrainProvider: await createWorldBathymetryAsync({
 *       requestVertexNormals: true
 *     });
 *   });
 * } catch (error) {
 *   console.log(error);
 * }
 * ```
 * @return A promise that resolves to the created CesiumTerrainProvider
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#createWorldBathymetryAsync">Online Documentation</a>
 */
external  fun createWorldBathymetryAsync ( options: CreateWorldBathymetryAsyncOptions? = definedExternally): Promise<CesiumTerrainProvider>

/**
 * @property [requestVertexNormals] Flag that indicates if the client should request additional lighting information from the server if available.
 *   Default value - `false`
 */
external  interface CreateWorldBathymetryAsyncOptions {
var  requestVertexNormals: Boolean?
}