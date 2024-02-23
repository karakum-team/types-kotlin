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
 * Contains functions for finding the Cartesian coordinates of the sun and the moon in the
 * Earth-centered inertial frame.
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Simon1994PlanetaryPositions.html">Online Documentation</a>
 */
external  object Simon1994PlanetaryPositions   {
/**
 * Computes the position of the Sun in the Earth-centered inertial frame
 * @param [julianDate] The time at which to compute the Sun's position, if not provided the current system time is used.
 * @param [result] The object onto which to store the result.
 * @return Calculated sun position
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Simon1994PlanetaryPositions.html#.computeSunPositionInEarthInertialFrame">Online Documentation</a>
 */
 fun computeSunPositionInEarthInertialFrame (
 julianDate: JulianDate? = definedExternally,
 result: Cartesian3? = definedExternally
): Cartesian3

/**
 * Computes the position of the Moon in the Earth-centered inertial frame
 * @param [julianDate] The time at which to compute the Sun's position, if not provided the current system time is used.
 * @param [result] The object onto which to store the result.
 * @return Calculated moon position
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/Simon1994PlanetaryPositions.html#.computeMoonPositionInEarthInertialFrame">Online Documentation</a>
 */
 fun computeMoonPositionInEarthInertialFrame (
 julianDate: JulianDate? = definedExternally,
 result: Cartesian3? = definedExternally
): Cartesian3
}
