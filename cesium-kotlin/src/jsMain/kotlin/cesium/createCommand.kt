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
 * Create a Command from a given function, for use with ViewModels.
 * 
 * A Command is a function with an extra `canExecute` observable property to determine
 * whether the command can be executed.  When executed, a Command function will check the
 * value of `canExecute` and throw if false.  It also provides events for when
 * a command has been or is about to be executed.
 * @param [func] The function to execute.
 * @param [canExecute] A boolean indicating whether the function can currently be executed.
 *   Default value - `true`
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#createCommand">Online Documentation</a>
 */
external  fun createCommand (
 func: Function<*>,
 canExecute: Boolean? = definedExternally
)