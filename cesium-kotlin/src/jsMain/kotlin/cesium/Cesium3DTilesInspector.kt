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
 * Inspector widget to aid in debugging 3D Tiles
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Cesium3DTilesInspector.html">Online Documentation</a>
 * 
 * @constructor
 * @property [container] The DOM element that will contain the widget.
 * @param [scene] the Scene instance to use.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Cesium3DTilesInspector.html">Online Documentation</a>
 */
external  class Cesium3DTilesInspector (
 var container: Element,
 scene: Scene
)  {
/**
 * Gets the view model.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Cesium3DTilesInspector.html#viewModel">Online Documentation</a>
 */
var viewModel: Cesium3DTilesInspectorViewModel

/**
 * @return true if the object has been destroyed, false otherwise.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Cesium3DTilesInspector.html#isDestroyed">Online Documentation</a>
 */
 fun isDestroyed (): Boolean

/**
 * Destroys the widget.  Should be called if permanently
 * removing the widget from layout.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Cesium3DTilesInspector.html#destroy">Online Documentation</a>
 */
 fun destroy ()
}
