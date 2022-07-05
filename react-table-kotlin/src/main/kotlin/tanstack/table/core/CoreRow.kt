// Automatically generated - do not modify!

package tanstack.table.core

import kotlinx.js.ReadonlyArray

external interface CoreRow<TData : RowData> {
    var id: String
    var index: Number
    var original: TData?
    var depth: Number
    var getValue: (columnId: string) -> any
    var renderValue: (columnId: string) -> unknown
    var subRows: ReadonlyArray<Row<TData>>
    var getLeafRows: () -> Row<TData>[]
    var originalSubRows: TData[]?
    var getAllCells: () -> ReadonlyArray<Cell<TData>>
}
