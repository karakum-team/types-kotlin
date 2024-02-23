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
 * Pauses the KmlTour for a given number of seconds.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlTourWait.html">Online Documentation</a>
 * 
 * @constructor
 * @param [duration] entry duration
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlTourWait.html">Online Documentation</a>
 */
external  class KmlTourWait ( duration: Double)  {
/**
 * Play this playlist entry
 * @param [done] function which will be called when playback ends
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlTourWait.html#play">Online Documentation</a>
 */
 fun play ( done: DoneCallback)

/**
 * Stop execution of curent entry, cancel curent timeout
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlTourWait.html#stop">Online Documentation</a>
 */
 fun stop ()
}
