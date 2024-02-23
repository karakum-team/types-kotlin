// Automatically generated - do not modify!

package react.dom.events

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue
import web.dom.Element
import web.window.Window
import web.events.Event
import web.events.EventTarget

external interface BaseSyntheticEvent<out E : Any, out C : Any, out T : Any> {
val nativeEvent: E
val currentTarget: C
val target: T
val bubbles: Boolean
val cancelable: Boolean
val defaultPrevented: Boolean
val eventPhase: Int
val isTrusted: Boolean
fun preventDefault()
fun isDefaultPrevented(): Boolean
fun stopPropagation()
fun isPropagationStopped(): Boolean
fun persist()
val timeStamp: web.time.DOMHighResTimeStamp
val type: String
}
