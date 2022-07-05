// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnPinningRow<TData : RowData> {
    var getLeftVisibleCells: () => Cell<TData>[]
    var getCenterVisibleCells: () => Cell<TData>[]
    var getRightVisibleCells: () => Cell<TData>[]
}
