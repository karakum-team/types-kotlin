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
 * A [Visualizer] which maps [Entity.tileset] to a [Cesium3DTileset].
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Cesium3DTilesetVisualizer.html">Online Documentation</a>
 * 
 * @constructor
 * @param [scene] The scene the primitives will be rendered in.
 * @param [entityCollection] The entityCollection to visualize.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Cesium3DTilesetVisualizer.html">Online Documentation</a>
 */
external  class Cesium3DTilesetVisualizer (
 scene: Scene,
 entityCollection: EntityCollection
)  : Visualizer {
/**
 * Updates models created this visualizer to match their
 * Entity counterpart at the given time.
 * @param [time] The time to update to.
 * @return This function always returns true.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Cesium3DTilesetVisualizer.html#update">Online Documentation</a>
 */
override  fun update ( time: JulianDate): Boolean

/**
 * Returns true if this object was destroyed; otherwise, false.
 * @return True if this object was destroyed; otherwise, false.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Cesium3DTilesetVisualizer.html#isDestroyed">Online Documentation</a>
 */
override  fun isDestroyed (): Boolean

/**
 * Removes and destroys all primitives created by this instance.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Cesium3DTilesetVisualizer.html#destroy">Online Documentation</a>
 */
override  fun destroy ()
}
