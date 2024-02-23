// Automatically generated - do not modify!

package react.dom.events

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue
import web.dom.Element
import web.window.Window
import web.events.Event
import web.events.EventTarget

external interface WheelEvent<out T : Element> : MouseEvent<T, NativeWheelEvent> {
val deltaMode: Int
val deltaX: Double
val deltaY: Double
val deltaZ: Double
}
