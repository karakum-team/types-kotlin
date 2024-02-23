// Automatically generated - do not modify!

package react.dom.events

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue
import web.dom.Element
import web.window.Window
import web.events.Event
import web.events.EventTarget

external interface PointerEvent<out T : Element> : MouseEvent<T, NativePointerEvent> {
val pointerId: Int
val pressure: Double
val tangentialPressure: Double
val tiltX: Double
val tiltY: Double
val twist: Int
val width: Double
val height: Double
val pointerType: PointerType
val isPrimary: Boolean
}
