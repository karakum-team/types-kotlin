// Automatically generated - do not modify!

package dom.html

sealed external class HTMLSelectElement :
    HTMLElement {
    var autocomplete: String
    var disabled: Boolean
    val form: HTMLFormElement?
    val labels: NodeListOf<HTMLLabelElement>
    var length: Number
    var multiple: Boolean
    var name: String
    val options: HTMLOptionsCollection
    var required: Boolean
    var selectedIndex: Number
    val selectedOptions: HTMLCollectionOf<HTMLOptionElement>
    var size: Number
    val type: String
    val validationMessage: String
    val validity: ValidityState
    var value: String
    val willValidate: Boolean
    fun add(
        element: HTMLOptionElement | HTMLOptGroupElement,
    before: HTMLElement | number??,
    )
    fun checkValidity(): Boolean
    fun item(index: Number): HTMLOptionElement?
    fun namedItem(name: String): HTMLOptionElement?
    fun remove()
    fun remove(index: Number)
    fun reportValidity(): Boolean
    fun setCustomValidity(error: String)
    // [name: number]: HTMLOptionElement | HTMLOptGroupElement
}
