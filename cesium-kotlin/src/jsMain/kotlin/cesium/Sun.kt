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
 * Draws a sun billboard.
 * This is only supported in 3D and Columbus view.
 * ```
 * scene.sun = new Sun();
 * ```
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Sun.html">Online Documentation</a>
 */
external  class Sun ()  {
/**
 * Determines if the sun will be shown.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Sun.html#show">Online Documentation</a>
 */
var show: Boolean

/**
 * Gets or sets a number that controls how "bright" the Sun's lens flare appears
 * to be.  Zero shows just the Sun's disc without any flare.
 * Use larger values for a more pronounced flare around the Sun.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Sun.html#glowFactor">Online Documentation</a>
 */
var glowFactor: Double

/**
 * Returns true if this object was destroyed; otherwise, false.
 * 
 * If this object was destroyed, it should not be used; calling any function other than
 * `isDestroyed` will result in a [DeveloperError] exception.
 * @return `true` if this object was destroyed; otherwise, `false`.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Sun.html#isDestroyed">Online Documentation</a>
 */
 fun isDestroyed (): Boolean

/**
 * Destroys the WebGL resources held by this object.  Destroying an object allows for deterministic
 * release of WebGL resources, instead of relying on the garbage collector to destroy this object.
 * 
 * Once an object is destroyed, it should not be used; calling any function other than
 * `isDestroyed` will result in a [DeveloperError] exception.  Therefore,
 * assign the return value (`undefined`) to the object as done in the example.
 * ```
 * sun = sun && sun.destroy();
 * ```
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Sun.html#destroy">Online Documentation</a>
 */
 fun destroy ()
}
