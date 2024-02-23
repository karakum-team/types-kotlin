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
 * Since KML does not support glTF models, this callback is required to specify what URL to use for the model in the KML document.
 * It can also be used to add additional files to the `externalFiles` object, which is the list of files embedded in the exported KMZ,
 * or otherwise returned with the KML string when exporting.
 * @param [model] The ModelGraphics instance for an Entity.
 * @param [time] The time that any properties should use to get the value.
 * @param [externalFiles] An object that maps a filename to a Blob or a Promise that resolves to a Blob.
 */
typealias exportKmlModelCallback = ( model: ModelGraphics,   time: JulianDate,   externalFiles: Any) -> String