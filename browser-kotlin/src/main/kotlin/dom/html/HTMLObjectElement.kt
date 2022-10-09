// Automatically generated - do not modify!

package dom.html

import dom.Document

sealed external class HTMLObjectElement :
    HTMLElement {
    var align: String
    var archive: String
    var border: String
    var code: String
    var codeBase: String
    var codeType: String
    val contentDocument: Document?
    val contentWindow: WindowProxy?
    var data: String
    var declare: Boolean
    val form: HTMLFormElement?
    var height: String
    var hspace: Number
    var name: String
    var standby: String
    var type: String
    var useMap: String
    val validationMessage: String
    val validity: ValidityState
    var vspace: Number
    var width: String
    val willValidate: Boolean
    fun checkValidity(): Boolean
    fun getSVGDocument(): Document?
    fun reportValidity(): Boolean
    fun setCustomValidity(error: String)
}
