// Automatically generated - do not modify!

@file:JsModule("cesium")

@file:Suppress(
"NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
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
 * A view model which exposes the properties of a toggle button.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/ToggleButtonViewModel.html">Online Documentation</a>
 * 
 * @constructor
 * @property [command] The command which will be executed when the button is toggled.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/ToggleButtonViewModel.html">Online Documentation</a>
 */
external  class ToggleButtonViewModel ( var command: Command)  {
/**
 * Gets or sets whether the button is currently toggled.  This property is observable.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/ToggleButtonViewModel.html#toggled">Online Documentation</a>
 */
var toggled: Boolean

/**
 * Gets or sets the button's tooltip.  This property is observable.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/ToggleButtonViewModel.html#tooltip">Online Documentation</a>
 */
var tooltip: String
}
