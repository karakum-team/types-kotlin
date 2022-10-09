// Automatically generated - do not modify!

package dom.html

sealed external class HTMLFormElement :
    HTMLElement {
    var acceptCharset: String
    var action: String
    var autocomplete: String
    val elements: HTMLFormControlsCollection
    var encoding: String
    var enctype: String
    val length: Number
    var method: String
    var name: String
    var noValidate: Boolean
    var target: String
    fun checkValidity(): Boolean
    fun reportValidity(): Boolean
    fun requestSubmit(submitter: HTMLElement??)
    fun reset()
    fun submit()
    // [index: number]: Element
    // [name: string]: any
}
