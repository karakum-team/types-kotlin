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
 * Determines if a point is inside a triangle.
 * ```
 * // Returns true
 * const p = new Cartesian2(0.25, 0.25);
 * const b = pointInsideTriangle(p,
 *   new Cartesian2(0.0, 0.0),
 *   new Cartesian2(1.0, 0.0),
 *   new Cartesian2(0.0, 1.0));
 * ```
 * @param [point] The point to test.
 * @param [p0] The first point of the triangle.
 * @param [p1] The second point of the triangle.
 * @param [p2] The third point of the triangle.
 * @return `true` if the point is inside the triangle; otherwise, `false`.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#pointInsideTriangle">Online Documentation</a>
 */
external  fun pointInsideTriangle (
 point: Cartesian3,
 p0: Cartesian3,
 p1: Cartesian3,
 p2: Cartesian3
): Boolean