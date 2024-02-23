// Automatically generated - do not modify!

@file:JsModule("cesium")

@file:Suppress(
"EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER",
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
 * Constructs an exception object that is thrown due to an error that can occur at runtime, e.g.,
 * out of memory, could not compile shader, etc.  If a function may throw this
 * exception, the calling code should be prepared to catch it.
 * 
 * On the other hand, a [DeveloperError] indicates an exception due
 * to a developer error, e.g., invalid argument, that usually indicates a bug in the
 * calling code.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/RuntimeError.html">Online Documentation</a>
 * 
 * @constructor
 * @property [message] The error message for this exception.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/RuntimeError.html">Online Documentation</a>
 */
external  class RuntimeError ( override val message: String = definedExternally)  : JsError {
/**
 * 'RuntimeError' indicating that this exception was thrown due to a runtime error.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/RuntimeError.html#name">Online Documentation</a>
 */
val name: String

/**
 * The stack trace of this exception, if available.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/RuntimeError.html#stack">Online Documentation</a>
 */
val stack: String
}
