// Automatically generated - do not modify!

package web.uievents

import js.array.ReadonlyArray

external interface PointerEventInit :
    MouseEventInit,
    PointerEventOptions {
    override val coalescedEvents: ReadonlyArray<PointerEvent>?
    override val height: Double?
    override val isPrimary: Boolean?
    override val pointerId: Int?
    override val pointerType: String?
    override val predictedEvents: ReadonlyArray<PointerEvent>?
    override val pressure: Float?
    override val tangentialPressure: Float?
    override val tiltX: Int?
    override val tiltY: Int?
    override val twist: Int?
    override val width: Double?
}
