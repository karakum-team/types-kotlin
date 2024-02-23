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
 * Provides the type of time standards which JulianDate can take as input.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#TimeStandard">Online Documentation</a>
 */
sealed external interface TimeStandard {
companion object {

/**
 * Represents the coordinated Universal Time (UTC) time standard.
 * 
 * UTC is related to TAI according to the relationship
 * `UTC = TAI - deltaT` where `deltaT` is the number of leap
 * seconds which have been introduced as of the time in TAI.
 */
val UTC: TimeStandard

/**
 * Represents the International Atomic Time (TAI) time standard.
 * TAI is the principal time standard to which the other time standards are related.
 */
val TAI: TimeStandard
}
}