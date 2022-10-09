// Automatically generated - do not modify!

package dom.html

sealed external class HTMLOutputElement :
    HTMLElement {
    var defaultValue: String
    val form: HTMLFormElement?
    val htmlFor: DOMTokenList
    val labels: NodeListOf<HTMLLabelElement>
    var name: String
    val type: String
    val validationMessage: String
    val validity: ValidityState
    var value: String
    val willValidate: Boolean
    fun checkValidity(): Boolean
    fun reportValidity(): Boolean
    fun setCustomValidity(error: String)
}
