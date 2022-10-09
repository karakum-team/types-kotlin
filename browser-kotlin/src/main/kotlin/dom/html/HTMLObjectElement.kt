// Automatically generated - do not modify!

package dom.html

import dom.Document

sealed external class HTMLObjectElement :
    HTMLElement {
    val contentDocument: Document?
    val contentWindow: WindowProxy?
    var data: String
    val form: HTMLFormElement?
    var height: String
    var name: String
    var type: String
    var useMap: String
    val validationMessage: String
    val validity: ValidityState
    var width: String
    val willValidate: Boolean
    fun checkValidity(): Boolean
    fun getSVGDocument(): Document?
    fun reportValidity(): Boolean
    fun setCustomValidity(error: String)
}
