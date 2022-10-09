// Automatically generated - do not modify!

package dom.html

sealed external class HTMLFieldSetElement :
    HTMLElement {
    var disabled: Boolean
    val elements: HTMLCollection
    val form: HTMLFormElement?
    var name: String
    val type: String
    val validationMessage: String
    val validity: ValidityState
    val willValidate: Boolean
    fun checkValidity(): Boolean
    fun reportValidity(): Boolean
    fun setCustomValidity(error: String)
}
