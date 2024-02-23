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
 * Creates a [Primitive] to visualize well-known vector vertex attributes:
 * `normal`, `tangent`, and `bitangent`.  Normal
 * is red; tangent is green; and bitangent is blue.  If an attribute is not
 * present, it is not drawn.
 * ```
 * scene.primitives.add(createTangentSpaceDebugPrimitive({
 *    geometry : instance.geometry,
 *    length : 100000.0,
 *    modelMatrix : instance.modelMatrix
 * }));
 * ```
 * @return A new `Primitive` instance with geometry for the vectors.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#createTangentSpaceDebugPrimitive">Online Documentation</a>
 */
external  fun createTangentSpaceDebugPrimitive ( options: CreateTangentSpaceDebugPrimitiveOptions): Primitive

/**
 * @property [geometry] The `Geometry` instance with the attribute.
 * @property [length] The length of each line segment in meters.  This can be negative to point the vector in the opposite direction.
 *   Default value - `10000.0`
 * @property [modelMatrix] The model matrix that transforms to transform the geometry from model to world coordinates.
 *   Default value - [Matrix4.IDENTITY]
 */
external  interface CreateTangentSpaceDebugPrimitiveOptions {
var  geometry: Geometry
var  length: Int?
var  modelMatrix: Matrix4?
}