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
 * Options to control the setting up of a WebGL Context.
 * 
 * `allowTextureFilterAnisotropic` defaults to true, which enables
 * anisotropic texture filtering when the WebGL extension is supported.
 * Setting this to false will improve performance, but hurt visual quality,
 * especially for horizon views.
 * @property [requestWebgl1] If true and the browser supports it, use a WebGL 1 rendering context
 *   Default value - `false`
 * @property [allowTextureFilterAnisotropic] If true, use anisotropic filtering during texture sampling
 *   Default value - `true`
 * @property [webgl] WebGL options to be passed on to canvas.getContext
 * @property [getWebGLStub] A function to create a WebGL stub for testing
 */
external interface ContextOptions {
var  requestWebgl1: Boolean?
var  allowTextureFilterAnisotropic: Boolean?
var  webgl: WebGLOptions?
var  getWebGLStub: Function<*>?
}