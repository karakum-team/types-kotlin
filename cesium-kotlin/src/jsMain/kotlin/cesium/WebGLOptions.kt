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
 * WebGL options to be passed on to HTMLCanvasElement.getContext().
 * See [WebGLContextAttributes](https://registry.khronos.org/webgl/specs/latest/1.0/#5.2)
 * but note the modified defaults for 'alpha', 'stencil', and 'powerPreference'
 * 
 * `alpha` defaults to false, which can improve performance
 * compared to the standard WebGL default of true.  If an application needs
 * to composite Cesium above other HTML elements using alpha-blending, set
 * `alpha` to true.
 */
external interface WebGLOptions {
var  alpha: Boolean?
var  depth: Boolean?
var  stencil: Boolean?
var  antialias: Boolean?
var  premultipliedAlpha: Boolean?
var  preserveDrawingBuffer: Boolean?
var  powerPreference: dynamic
var  failIfMajorPerformanceCaveat: Boolean?
}