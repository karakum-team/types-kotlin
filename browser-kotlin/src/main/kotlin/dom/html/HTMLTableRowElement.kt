// Automatically generated - do not modify!

package dom.html

sealed external class HTMLTableRowElement :
    HTMLElement {
    val cells: HTMLCollectionOf<HTMLTableCellElement>
    val rowIndex: Number
    val sectionRowIndex: Number
    fun deleteCell(index: Number)
    fun insertCell(index: Number = definedExternally): HTMLTableCellElement
}
