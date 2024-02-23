// Automatically generated - do not modify!

package web.uievents

import js.objects.JsPlainObject
import web.events.Event
import web.events.EventInit
import web.keyboard.KeyCode

@JsPlainObject
external interface KeyboardEventInit :
EventModifierInit  {
val code: KeyCode?
val isComposing: Boolean?
val key: String?
val location: KeyLocation?
val repeat: Boolean?
}
