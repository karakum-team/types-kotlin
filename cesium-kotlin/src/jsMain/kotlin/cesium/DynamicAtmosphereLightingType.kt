// Automatically generated - do not modify!

@file:JsModule("cesium")

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
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
 * Atmosphere lighting effects (sky atmosphere, ground atmosphere, fog) can be
 * further modified with dynamic lighting from the sun or other light source
 * that changes over time. This enum determines which light source to use.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#DynamicAtmosphereLightingType">Online Documentation</a>
 */
sealed external interface DynamicAtmosphereLightingType {
companion object {

/**
 * Do not use dynamic atmosphere lighting. Atmosphere lighting effects will
 * be lit from directly above rather than using the scene's light source.
 */
val NONE: DynamicAtmosphereLightingType

/**
 * Use the scene's current light source for dynamic atmosphere lighting.
 */
val SCENE_LIGHT: DynamicAtmosphereLightingType

/**
 * Force the dynamic atmosphere lighting to always use the sunlight direction,
 * even if the scene uses a different light source.
 */
val SUNLIGHT: DynamicAtmosphereLightingType
}
}