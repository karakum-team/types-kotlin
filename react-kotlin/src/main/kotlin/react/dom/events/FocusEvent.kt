// Automatically generated - do not modify!

package react.dom.events

import org.w3c.dom.Element
import web.events.EventTarget

external interface FocusEvent<out T : Element> : SyntheticEvent<T, NativeFocusEvent> {
    val relatedTarget: EventTarget?
    override val target: T
}
