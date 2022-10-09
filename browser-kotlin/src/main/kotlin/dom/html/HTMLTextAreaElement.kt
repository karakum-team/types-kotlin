// Automatically generated - do not modify!

package dom.html

import dom.NodeListOf

sealed external class HTMLTextAreaElement :
    HTMLElement {
    var autocomplete: String
    var cols: Number
    var defaultValue: String
    var dirName: String
    var disabled: Boolean
    val form: HTMLFormElement?
    val labels: NodeListOf<HTMLLabelElement>
    var maxLength: Number
    var minLength: Number
    var name: String
    var placeholder: String
    var readOnly: Boolean
    var required: Boolean
    var rows: Number
    var selectionDirection: String /* "forward" | "backward" | "none" */
    var selectionEnd: Number
    var selectionStart: Number
    val textLength: Number
    val type: String
    val validationMessage: String
    val validity: ValidityState
    var value: String
    val willValidate: Boolean
    var wrap: String
    fun checkValidity(): Boolean
    fun reportValidity(): Boolean
    fun select()
    fun setCustomValidity(error: String)
    fun setRangeText(replacement: String)
    fun setRangeText(
        replacement: String,
        start: Number,
        end: Number,
        selectionMode: SelectionMode?,
    )

    fun setSelectionRange(
        start: number?,
        end: number?,
        direction: String /* "forward" | "backward" | "none" */?,
    )
}
