// Automatically generated - do not modify!

@file:JsModule("react-query")
@file:JsNonModule

@file:Suppress(
    "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
)

package react.query

typealias SetupFn = (setFocused: (focused: Boolean?) -> Unit) -> (() -> Unit)?

open external class FocusManager : Subscribable<Listener> {
    override fun onSubscribe()
    override fun onUnsubscribe()
    open fun setEventListener(setup: SetupFn)
    open fun setFocused(focused: Boolean = definedExternally)
    open fun onFocus()
    open fun isFocused(): Boolean
}

external val focusManager: FocusManager
