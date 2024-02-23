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
 * Constants related to ISO8601 support.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Iso8601.html">Online Documentation</a>
 */
external  object Iso8601   {
/**
 * A [JulianDate] representing the earliest time representable by an ISO8601 date.
 * This is equivalent to the date string '0000-01-01T00:00:00Z'
 */
val MINIMUM_VALUE: JulianDate

/**
 * A [JulianDate] representing the latest time representable by an ISO8601 date.
 * This is equivalent to the date string '9999-12-31T24:00:00Z'
 */
val MAXIMUM_VALUE: JulianDate

/**
 * A [TimeInterval] representing the largest interval representable by an ISO8601 interval.
 * This is equivalent to the interval string '0000-01-01T00:00:00Z/9999-12-31T24:00:00Z'
 */
val MAXIMUM_INTERVAL: TimeInterval
}
