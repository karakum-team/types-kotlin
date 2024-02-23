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
 * A mixin which adds the [PerformanceWatchdog] widget to the [Viewer] widget.
 * Rather than being called directly, this function is normally passed as
 * a parameter to [Viewer.extend], as shown in the example below.
 * ```
 * const viewer = new Viewer('cesiumContainer');
 * viewer.extend(viewerPerformanceWatchdogMixin, {
 *     lowFrameRateMessage : 'Why is this going so <em>slowly</em>?'
 * });
 * ```
 * @param [viewer] The viewer instance.
 * @param [options] An object with properties.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#viewerPerformanceWatchdogMixin">Online Documentation</a>
 */
external  fun viewerPerformanceWatchdogMixin (
 viewer: Viewer,
 options: ViewerPerformanceWatchdogMixinOptions? = definedExternally
)

/**
 * @property [lowFrameRateMessage] The
 *   message to display when a low frame rate is detected.  The message is interpeted as HTML, so make sure
 *   it comes from a trusted source so that your application is not vulnerable to cross-site scripting attacks.
 *   Default value - `'This application appears to be performing poorly on your system.  Please try using a different web browser or updating your video drivers.'`
 */
external  interface ViewerPerformanceWatchdogMixinOptions {
var  lowFrameRateMessage: String?
}