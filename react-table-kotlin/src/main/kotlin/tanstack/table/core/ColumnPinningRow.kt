// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnPinningRow<TData extends RowData> = {
    getLeftVisibleCells: () => Cell<TData>[]
    getCenterVisibleCells: () => Cell<TData>[]
    getRightVisibleCells: () => Cell<TData>[]
}
