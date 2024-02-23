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
 * An enum of the basic GLSL uniform types. These can be used with
 * [CustomShader] to declare user-defined uniforms.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#UniformType">Online Documentation</a>
 */
sealed external interface UniformType {
companion object {

/**
 * A single floating point value.
 */
val FLOAT: UniformType

/**
 * A vector of 2 floating point values.
 */
val VEC2: UniformType

/**
 * A vector of 3 floating point values.
 */
val VEC3: UniformType

/**
 * A vector of 4 floating point values.
 */
val VEC4: UniformType

/**
 * A single integer value
 */
val INT: UniformType

/**
 * A vector of 2 integer values.
 */
val INT_VEC2: UniformType

/**
 * A vector of 3 integer values.
 */
val INT_VEC3: UniformType

/**
 * A vector of 4 integer values.
 */
val INT_VEC4: UniformType

/**
 * A single boolean value.
 */
val BOOL: UniformType

/**
 * A vector of 2 boolean values.
 */
val BOOL_VEC2: UniformType

/**
 * A vector of 3 boolean values.
 */
val BOOL_VEC3: UniformType

/**
 * A vector of 4 boolean values.
 */
val BOOL_VEC4: UniformType

/**
 * A 2x2 matrix of floating point values.
 */
val MAT2: UniformType

/**
 * A 3x3 matrix of floating point values.
 */
val MAT3: UniformType

/**
 * A 3x3 matrix of floating point values.
 */
val MAT4: UniformType

/**
 * A 2D sampled texture.
 */
val SAMPLER_2D: UniformType

val SAMPLER_CUBE: UniformType
}
}