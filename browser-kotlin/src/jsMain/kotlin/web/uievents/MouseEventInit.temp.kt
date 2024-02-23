// Automatically generated - do not modify!

package web.uievents

import js.objects.JsPlainObject
import js.objects.jso
import web.events.Event
import web.events.EventInit
import web.events.EventTarget
import web.events.EventInitMutable

inline fun MouseEventInit(
    block: MouseEventInitMutable.() -> Unit,
): MouseEventInit =
    jso(block)

@JsPlainObject
external interface MouseEventInitMutable :
MouseEventInit,
EventModifierInitMutable  {
override var button: MouseButton?
override var buttons: MouseButtons?
override var clientX: Int?
override var clientY: Int?
override var movementX: Double?
override var movementY: Double?
override var relatedTarget: EventTarget?
override var screenX: Int?
override var screenY: Int?
}
