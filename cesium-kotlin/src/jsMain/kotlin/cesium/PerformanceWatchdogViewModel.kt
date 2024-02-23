// Automatically generated - do not modify!

@file:JsModule("cesium")

@file:Suppress(
"NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
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
 * The view model for [PerformanceWatchdog].
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PerformanceWatchdogViewModel.html">Online Documentation</a>
 */
external  class PerformanceWatchdogViewModel ()  {
/**
 * Gets or sets the message to display when a low frame rate is detected.  This string will be interpreted as HTML.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PerformanceWatchdogViewModel.html#lowFrameRateMessage">Online Documentation</a>
 */
var lowFrameRateMessage: String

/**
 * Gets or sets a value indicating whether the low frame rate message has previously been dismissed by the user.  If it has
 * been dismissed, the message will not be redisplayed, no matter the frame rate.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PerformanceWatchdogViewModel.html#lowFrameRateMessageDismissed">Online Documentation</a>
 */
var lowFrameRateMessageDismissed: Boolean

/**
 * Gets or sets a value indicating whether the low frame rate message is currently being displayed.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PerformanceWatchdogViewModel.html#showingLowFrameRateMessage">Online Documentation</a>
 */
var showingLowFrameRateMessage: Boolean

/**
 * Gets the [Scene] instance for which to monitor performance.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PerformanceWatchdogViewModel.html#scene">Online Documentation</a>
 */
var scene: Scene

/**
 * Gets a command that dismisses the low frame rate message.  Once it is dismissed, the message
 * will not be redisplayed.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/PerformanceWatchdogViewModel.html#dismissMessage">Online Documentation</a>
 */
var dismissMessage: Command
}
inline fun PerformanceWatchdogViewModel(
    block: PerformanceWatchdogViewModel.() -> Unit
): PerformanceWatchdogViewModel =
    PerformanceWatchdogViewModel().apply(block)