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
 * The view model for [VRButton].
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/VRButtonViewModel.html">Online Documentation</a>
 * 
 * @constructor
 * @param [scene] The scene.
 * @property [vrElement] The element or id to be placed into VR mode.
 *   Default value - `document.body`
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/VRButtonViewModel.html">Online Documentation</a>
 */
external  class VRButtonViewModel (
 scene: Scene,
 var vrElement: Element = definedExternally
)  {
/**
 * Gets whether or not VR mode is active.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/VRButtonViewModel.html#isVRMode">Online Documentation</a>
 */
var isVRMode: Boolean

/**
 * Gets or sets whether or not VR functionality should be enabled.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/VRButtonViewModel.html#isVREnabled">Online Documentation</a>
 */
var isVREnabled: Boolean

/**
 * Gets the tooltip.  This property is observable.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/VRButtonViewModel.html#tooltip">Online Documentation</a>
 */
var tooltip: String

/**
 * Gets the Command to toggle VR mode.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/VRButtonViewModel.html#command">Online Documentation</a>
 */
var command: Command

/**
 * @return true if the object has been destroyed, false otherwise.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/VRButtonViewModel.html#isDestroyed">Online Documentation</a>
 */
 fun isDestroyed (): Boolean

/**
 * Destroys the view model.  Should be called to
 * properly clean up the view model when it is no longer needed.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/VRButtonViewModel.html#destroy">Online Documentation</a>
 */
 fun destroy ()
}
