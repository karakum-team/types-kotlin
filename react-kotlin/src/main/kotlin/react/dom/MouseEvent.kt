// Automatically generated - do not modify!

package react.dom

import org.w3c.dom.Element
import org.w3c.dom.events.EventTarget

external interface MouseEvent<T : Element, E : NativeMouseEvent> : UIEvent<T, E> {
    val altKey: Boolean
    val button: Number
    val buttons: Number
    val clientX: Number
    val clientY: Number
    val ctrlKey: Boolean

    /**
     * See [DOM Level 3 Events spec](https://www.w3.org/TR/uievents-key/#keys-modifier). for a list of valid (case-sensitive) arguments to this method.
     */
    fun getModifierState(key: String): Boolean
    val metaKey: Boolean
    val movementX: Number
    val movementY: Number
    val pageX: Number
    val pageY: Number
    val relatedTarget: EventTarget?
    val screenX: Number
    val screenY: Number
    val shiftKey: Boolean
}
