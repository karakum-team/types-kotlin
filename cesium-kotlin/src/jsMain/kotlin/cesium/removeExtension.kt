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
 * Removes an extension from gltf.extensions, gltf.extensionsUsed, gltf.extensionsRequired, and any other objects in the glTF if it is present.
 * @param [gltf] A javascript object containing a glTF asset.
 * @param [extension] The extension to remove.
 * @return The extension data removed from gltf.extensions.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#removeExtension">Online Documentation</a>
 */
external  fun removeExtension (
 gltf: Any,
 extension: String
): Any