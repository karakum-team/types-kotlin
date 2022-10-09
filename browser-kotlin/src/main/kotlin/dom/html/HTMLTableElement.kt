// Automatically generated - do not modify!

package dom.html

sealed external class HTMLTableElement :
    HTMLElement {
    var align: String
    var bgColor: String
    var border: String
    var caption: HTMLTableCaptionElement?
    var cellPadding: String
    var cellSpacing: String
    var frame: String
    val rows: HTMLCollectionOf<HTMLTableRowElement>
    var rules: String
    var summary: String
    val tBodies: HTMLCollectionOf<HTMLTableSectionElement>
    var tFoot: HTMLTableSectionElement?
    var tHead: HTMLTableSectionElement?
    var width: String
    fun createCaption(): HTMLTableCaptionElement
    fun createTBody(): HTMLTableSectionElement
    fun createTFoot(): HTMLTableSectionElement
    fun createTHead(): HTMLTableSectionElement
    fun deleteCaption()
    fun deleteRow(index: Number)
    fun deleteTFoot()
    fun deleteTHead()
    fun insertRow(index: Number?): HTMLTableRowElement
}
