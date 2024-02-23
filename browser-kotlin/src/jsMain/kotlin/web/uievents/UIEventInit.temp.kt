// Automatically generated - do not modify!

package web.uievents

import js.objects.JsPlainObject
import js.objects.jso
import web.events.Event
import web.events.EventInit
import web.window.Window
import web.events.EventInitMutable

inline fun UIEventInit(
    block: UIEventInitMutable.() -> Unit,
): UIEventInit =
    jso(block)

@JsPlainObject
external interface UIEventInitMutable :
UIEventInit,
EventInitMutable  {
override var detail: Int?
override var view: Window?
}
