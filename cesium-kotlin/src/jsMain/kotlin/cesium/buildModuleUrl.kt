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
 * Given a relative URL under the Cesium base URL, returns an absolute URL.
 * ```
 * const viewer = new Viewer("cesiumContainer", {
 *   baseLayer: ImageryLayer.fromProviderAsync(
 *     TileMapServiceImageryProvider.fromUrl(
 *       buildModuleUrl("Assets/Textures/NaturalEarthII"),
 *     )),
 *   baseLayerPicker: false,
 * });
 * ```
 * @param [relativeUrl] The relative path.
 * @return The absolutely URL representation of the provided path.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#buildModuleUrl">Online Documentation</a>
 */
external  fun buildModuleUrl ( relativeUrl: String): String