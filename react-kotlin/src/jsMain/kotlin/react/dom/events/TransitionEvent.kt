// Automatically generated - do not modify!

package react.dom.events

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue
import web.dom.Element
import web.window.Window
import web.events.Event
import web.events.EventTarget

external interface TransitionEvent<out T : Element> : SyntheticEvent<T, NativeTransitionEvent> {
val elapsedTime: Double
val propertyName: String
val pseudoElement: String
}
