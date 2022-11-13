// Automatically generated - do not modify!

package dom.geometry

import kotlinx.js.Float32Array
import kotlinx.js.Float64Array
import kotlinx.js.ReadonlyArray

open external class DOMMatrixReadOnly(
    init: ReadonlyArray<Double> /* | String */ = definedExternally,
) {
    val a: Double
    val b: Double
    val c: Double
    val d: Double
    val e: Double
    val f: Double
    val is2D: Boolean
    val isIdentity: Boolean
    val m11: Double
    val m12: Double
    val m13: Double
    val m14: Double
    val m21: Double
    val m22: Double
    val m23: Double
    val m24: Double
    val m31: Double
    val m32: Double
    val m33: Double
    val m34: Double
    val m41: Double
    val m42: Double
    val m43: Double
    val m44: Double
    fun flipX(): DOMMatrix
    fun flipY(): DOMMatrix
    fun inverse(): DOMMatrix
    fun multiply(other: DOMMatrixInit = definedExternally): DOMMatrix
    fun rotate(
        rotX: Number = definedExternally,
        rotY: Number = definedExternally,
        rotZ: Number = definedExternally,
    ): DOMMatrix

    fun rotateAxisAngle(
        x: Number = definedExternally,
        y: Number = definedExternally,
        z: Number = definedExternally,
        angle: Number = definedExternally,
    ): DOMMatrix

    fun rotateFromVector(
        x: Number = definedExternally,
        y: Number = definedExternally,
    ): DOMMatrix

    fun scale(
        scaleX: Number = definedExternally,
        scaleY: Number = definedExternally,
        scaleZ: Number = definedExternally,
        originX: Number = definedExternally,
        originY: Number = definedExternally,
        originZ: Number = definedExternally,
    ): DOMMatrix

    fun scale3d(
        scale: Number = definedExternally,
        originX: Number = definedExternally,
        originY: Number = definedExternally,
        originZ: Number = definedExternally,
    ): DOMMatrix

    fun skewX(sx: Number = definedExternally): DOMMatrix
    fun skewY(sy: Number = definedExternally): DOMMatrix
    fun toFloat32Array(): Float32Array
    fun toFloat64Array(): Float64Array
    fun toJSON(): Any
    fun transformPoint(point: DOMPointInit = definedExternally): DOMPoint
    fun translate(
        tx: Number = definedExternally,
        ty: Number = definedExternally,
        tz: Number = definedExternally,
    ): DOMMatrix
}
