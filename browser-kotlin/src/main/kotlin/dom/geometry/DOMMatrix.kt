// Automatically generated - do not modify!

package dom.geometry

import kotlinx.js.ReadonlyArray

external class DOMMatrix(
    init: ReadonlyArray<Double> /* | String */ = definedExternally,
) : DOMMatrixReadOnly {
    var a: Double
    var b: Double
    var c: Double
    var d: Double
    var e: Double
    var f: Double
    var m11: Double
    var m12: Double
    var m13: Double
    var m14: Double
    var m21: Double
    var m22: Double
    var m23: Double
    var m24: Double
    var m31: Double
    var m32: Double
    var m33: Double
    var m34: Double
    var m41: Double
    var m42: Double
    var m43: Double
    var m44: Double
    fun invertSelf(): DOMMatrix
    fun multiplySelf(other: DOMMatrixInit = definedExternally): DOMMatrix
    fun preMultiplySelf(other: DOMMatrixInit = definedExternally): DOMMatrix
    fun rotateAxisAngleSelf(
        x: Number = definedExternally,
        y: Number = definedExternally,
        z: Number = definedExternally,
        angle: Number = definedExternally,
    ): DOMMatrix

    fun rotateFromVectorSelf(
        x: Number = definedExternally,
        y: Number = definedExternally,
    ): DOMMatrix

    fun rotateSelf(
        rotX: Number = definedExternally,
        rotY: Number = definedExternally,
        rotZ: Number = definedExternally,
    ): DOMMatrix

    fun scale3dSelf(
        scale: Number = definedExternally,
        originX: Number = definedExternally,
        originY: Number = definedExternally,
        originZ: Number = definedExternally,
    ): DOMMatrix

    fun scaleSelf(
        scaleX: Number = definedExternally,
        scaleY: Number = definedExternally,
        scaleZ: Number = definedExternally,
        originX: Number = definedExternally,
        originY: Number = definedExternally,
        originZ: Number = definedExternally,
    ): DOMMatrix

    fun setMatrixValue(transformList: String): DOMMatrix
    fun skewXSelf(sx: Number = definedExternally): DOMMatrix
    fun skewYSelf(sy: Number = definedExternally): DOMMatrix
    fun translateSelf(
        tx: Number = definedExternally,
        ty: Number = definedExternally,
        tz: Number = definedExternally,
    ): DOMMatrix
}
