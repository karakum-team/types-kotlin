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
 * Gets a timestamp that can be used in measuring the time between events.  Timestamps
 * are expressed in milliseconds, but it is not specified what the milliseconds are
 * measured from.  This function uses performance.now() if it is available, or Date.now()
 * otherwise.
 * @return The timestamp in milliseconds since some unspecified reference time.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#getTimestamp">Online Documentation</a>
 */
external  fun getTimestamp (): Double