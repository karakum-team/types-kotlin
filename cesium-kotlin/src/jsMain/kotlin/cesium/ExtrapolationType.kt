// Automatically generated - do not modify!

@file:JsModule("cesium")

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
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
 * Constants to determine how an interpolated value is extrapolated
 * when querying outside the bounds of available data.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#ExtrapolationType">Online Documentation</a>
 */
sealed external interface ExtrapolationType {
companion object {

/**
 * No extrapolation occurs.
 */
val NONE: ExtrapolationType

/**
 * The first or last value is used when outside the range of sample data.
 */
val HOLD: ExtrapolationType

/**
 * The value is extrapolated.
 */
val EXTRAPOLATE: ExtrapolationType
}
}