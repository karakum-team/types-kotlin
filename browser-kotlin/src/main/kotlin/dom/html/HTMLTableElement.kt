// Automatically generated - do not modify!

package dom.html

sealed external class HTMLTableElement :
    HTMLElement {
    var caption: HTMLTableCaptionElement?
    val rows: HTMLCollectionOf<HTMLTableRowElement>
    val tBodies: HTMLCollectionOf<HTMLTableSectionElement>
    var tFoot: HTMLTableSectionElement?
    var tHead: HTMLTableSectionElement?
    fun createCaption(): HTMLTableCaptionElement
    fun createTBody(): HTMLTableSectionElement
    fun createTFoot(): HTMLTableSectionElement
    fun createTHead(): HTMLTableSectionElement
    fun deleteCaption()
    fun deleteRow(index: Number)
    fun deleteTFoot()
    fun deleteTHead()
    fun insertRow(index: Number = definedExternally): HTMLTableRowElement
}
