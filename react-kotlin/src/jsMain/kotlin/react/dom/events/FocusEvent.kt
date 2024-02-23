// Automatically generated - do not modify!

package react.dom.events

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue
import web.dom.Element
import web.window.Window
import web.events.Event
import web.events.EventTarget

external interface FocusEvent<out T : Element> : SyntheticEvent<T, NativeFocusEvent> {
val relatedTarget: EventTarget?
override val target: T
}
