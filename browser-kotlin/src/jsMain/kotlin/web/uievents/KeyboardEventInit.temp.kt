// Automatically generated - do not modify!

package web.uievents

import web.keyboard.KeyCode

external interface KeyboardEventInitMutable :
    KeyboardEventInit, EventModifierInitMutable {
    override var code: KeyCode?
    override var isComposing: Boolean?
    override var key: String?
    override var location: KeyLocation?
    override var repeat: Boolean?
}
