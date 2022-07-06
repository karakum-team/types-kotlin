// Automatically generated - do not modify!

package tanstack.table.core

import kotlinx.js.ReadonlyArray

external interface CoreRow<TData : RowData> {
    var id: String
    var index: Number
    var original: TData?
    var depth: Number
    var getValue: (columnId: String) -> any
    var renderValue: (columnId: String) -> unknown
    var subRows: ReadonlyArray<Row<TData>>
    var getLeafRows: () -> Row<TData>[]
    var originalSubRows: ReadonlyArray<TData>?
    var getAllCells: () -> ReadonlyArray<Cell<TData>>
}
