// Automatically generated - do not modify!

package web.uievents

import web.events.EventTarget

external interface MouseEventInit :
    EventModifierInit,
    MouseEventOptions {
    override val button: MouseButton?
    override val buttons: MouseButtons?
    override val clientX: Int?
    override val clientY: Int?
    override val movementX: Double?
    override val movementY: Double?
    override val relatedTarget: EventTarget?
    override val screenX: Int?
    override val screenY: Int?
}
