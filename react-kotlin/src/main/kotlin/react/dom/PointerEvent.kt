// Automatically generated - do not modify!

package react.dom

import org.w3c.dom.Element

external interface PointerEvent<T : Element> : MouseEvent<T, NativePointerEvent> {
    val pointerId: Number
    val pressure: Number
    val tangentialPressure: Number
    val tiltX: Double
    val tiltY: Double
    val twist: Number
    val width: Double
    val height: Double
    val pointerType: String // 'mouse' | 'pen' | 'touch'
    val isPrimary: Boolean
}
