// Automatically generated - do not modify!

package dom.html

sealed external class HTMLButtonElement :
    HTMLElement {
    var disabled: Boolean
    val form: HTMLFormElement?
    var formAction: String
    var formEnctype: String
    var formMethod: String
    var formNoValidate: Boolean
    var formTarget: String
    val labels: NodeListOf<HTMLLabelElement>
    var name: String
    var type: String
    val validationMessage: String
    val validity: ValidityState
    var value: String
    val willValidate: Boolean
    fun checkValidity(): Boolean
    fun reportValidity(): Boolean
    fun setCustomValidity(error: String)
}
