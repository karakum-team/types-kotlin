// Automatically generated - do not modify!

package web.uievents

import web.keyboard.KeyCode

external interface KeyboardEventInit :
    EventModifierInit,
    KeyboardEventOptions {
    override val code: KeyCode?
    override val isComposing: Boolean?
    override val key: String?
    override val location: KeyLocation?
    override val repeat: Boolean?
}
