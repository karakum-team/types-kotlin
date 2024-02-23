// Automatically generated - do not modify!

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
 * An orientation given by numeric heading, pitch, and roll
 * @property [heading] The heading in radians
 *   Default value - `0.0`
 * @property [pitch] The pitch in radians
 *   Default value - `-Math.PI_OVER_TWO`
 * @property [roll] The roll in radians
 *   Default value - `0.0`
 */
external interface HeadingPitchRollValues : CameraOrientation {
var  heading: Double?
var  pitch: Double?
var  roll: Double?
}