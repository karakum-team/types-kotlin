// Automatically generated - do not modify!

package dom.html

sealed external class HTMLTableRowElement :
    HTMLElement {
    var align: String
    var bgColor: String
    val cells: HTMLCollectionOf<HTMLTableCellElement>
    var ch: String
    var chOff: String
    val rowIndex: Number
    val sectionRowIndex: Number
    var vAlign: String
    fun deleteCell(index: Number)
    fun insertCell(index: Number = definedExternally): HTMLTableCellElement
}
