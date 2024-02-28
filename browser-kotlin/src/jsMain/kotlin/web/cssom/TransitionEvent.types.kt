// Automatically generated - do not modify!

package web.cssom

import seskar.js.JsValue
import seskar.js.JsVirtual
import web.events.EventTarget
import web.events.EventType

@JsVirtual
sealed external class TransitionEventTypes :
    TransitionEventTypes_deprecated {

    @JsValue("transitioncancel")
    fun <C : EventTarget> transitionCancel(): EventType<TransitionEvent<C>>

    @JsValue("transitionend")
    fun <C : EventTarget> transitionEnd(): EventType<TransitionEvent<C>>

    @JsValue("transitionrun")
    fun <C : EventTarget> transitionRun(): EventType<TransitionEvent<C>>

    @JsValue("transitionstart")
    fun <C : EventTarget> transitionStart(): EventType<TransitionEvent<C>>
}