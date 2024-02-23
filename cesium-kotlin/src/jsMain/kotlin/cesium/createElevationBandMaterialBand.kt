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
 * @property [entries] A list of elevation entries. They will automatically be sorted from lowest to highest. If there is only one entry and `extendsDownards` and `extendUpwards` are both `false`, they will both be set to `true`.
 * @property [extendDownwards] If `true`, the band's minimum elevation color will extend infinitely downwards.
 *   Default value - `false`
 * @property [extendUpwards] If `true`, the band's maximum elevation color will extend infinitely upwards.
 *   Default value - `false`
 */
external interface createElevationBandMaterialBand {
var  entries: ReadonlyArray<createElevationBandMaterialEntry>
var  extendDownwards: Boolean?
var  extendUpwards: Boolean?
}