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
 * Represents a point in stereographic coordinates, which can be obtained by projecting a cartesian coordinate from one pole onto a tangent plane at the other pole.
 * The stereographic projection faithfully represents the relative directions of all great circles passing through its center point.
 * To faithfully represents angles everywhere, this is a conformal projection, which means points are projected onto an arbrary sphere.
 * @param [position] The steroegraphic coordinates.
 * @param [tangentPlane] The tangent plane onto which the point was projected.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/global.html#Stereographic">Online Documentation</a>
 */
external  fun Stereographic (
 position: Cartesian2? = definedExternally,
 tangentPlane: EllipseGeometry? = definedExternally
)