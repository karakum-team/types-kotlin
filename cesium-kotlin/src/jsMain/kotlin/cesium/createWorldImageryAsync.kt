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
 * Creates an [IonImageryProvider] instance for ion's default global base imagery layer, currently Bing Maps.
 * ```
 * // Create a Cesium World Imagery base layer with default settings
 * try {
 *   const imageryProvider = await createWorldImageryAsync();
 * } catch (error) {
 *   console.log(`There was an error creating world imagery: ${error}`);
 * }
 * ```
 * ```
 * // Create Cesium World Imagery with different style
 * try {
 *   const imageryProvider = await createWorldImageryAsync({
 *         style: IonWorldImageryStyle.AERIAL_WITH_LABELS
 *   });
 * } catch (error) {
 *   console.log(`There was an error creating world imagery: ${error}`);
 * }
 * ```
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#createWorldImageryAsync">Online Documentation</a>
 */
external  fun createWorldImageryAsync ( options: CreateWorldImageryAsyncOptions? = definedExternally): Promise<IonImageryProvider>

/**
 * @property [style] The style of base imagery, only AERIAL, AERIAL_WITH_LABELS, and ROAD are currently supported.
 *   Default value - [IonWorldImageryStyle]
 */
external  interface CreateWorldImageryAsyncOptions {
var  style: IonWorldImageryStyle?
}