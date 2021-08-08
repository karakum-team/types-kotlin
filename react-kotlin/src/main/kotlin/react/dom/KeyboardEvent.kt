// Automatically generated - do not modify!

package react.dom

import org.w3c.dom.Element

external interface KeyboardEvent<T : Element> : SyntheticEvent<T, NativeKeyboardEvent> {
    val altKey: Boolean

    /** @deprecated */
    val charCode: Number
    val ctrlKey: Boolean
    val code: String
    /**
     * See [DOM Level 3 Events spec](https://www.w3.org/TR/uievents-key/#keys-modifier). for a list of valid (case-sensitive) arguments to this method.
     */
    // getModifierState(key: string): boolean
    /**
     * See the [DOM Level 3 Events spec](https://www.w3.org/TR/uievents-key/#named-key-attribute-values). for possible values
     */
    val key: String

    /** @deprecated */
    val keyCode: Number
    val locale: String
    val location: Number
    val metaKey: Boolean
    val repeat: Boolean
    val shiftKey: Boolean

    /** @deprecated */
    val which: Number
}
