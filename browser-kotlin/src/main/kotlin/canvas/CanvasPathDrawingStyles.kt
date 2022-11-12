// Automatically generated - do not modify!

package canvas

import kotlinx.js.ReadonlyArray

sealed external interface CanvasPathDrawingStyles {
    var lineCap: CanvasLineCap
    var lineDashOffset: Double
    var lineJoin: CanvasLineJoin
    var lineWidth: Double
    var miterLimit: Double
    fun getLineDash(): Number[]
    fun setLineDash(segments: ReadonlyArray<number>)
}
