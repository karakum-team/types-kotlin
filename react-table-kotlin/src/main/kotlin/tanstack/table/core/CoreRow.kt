// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreRow<TData : RowData> {
    var id: String
    var index: number
    var original: TData?
    var depth: number
    var getValue: (columnId: string) -> any
    var renderValue: (columnId: string) -> unknown
    var subRows: Row<TData>[]
    var getLeafRows: () -> Row<TData>[]
    var originalSubRows: TData[]?
    var getAllCells: () -> Cell<TData>[]
}
