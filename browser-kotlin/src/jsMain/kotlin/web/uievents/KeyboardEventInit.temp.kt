// Automatically generated - do not modify!

package web.uievents

import js.objects.JsPlainObject
import js.objects.jso
import web.events.Event
import web.events.EventInit
import web.keyboard.KeyCode
import web.events.EventInitMutable

inline fun KeyboardEventInit(
    block: KeyboardEventInitMutable.() -> Unit,
): KeyboardEventInit =
    jso(block)

@JsPlainObject
external interface KeyboardEventInitMutable :
KeyboardEventInit,
EventModifierInitMutable  {
override var code: KeyCode?
override var isComposing: Boolean?
override var key: String?
override var location: KeyLocation?
override var repeat: Boolean?
}
