// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnPinningInstance<TData extends RowData> = {
    setColumnPinning: (updater: Updater<ColumnPinningState>) => void
    resetColumnPinning: (defaultState ?: boolean) => void
    getIsSomeColumnsPinned: (position ?: ColumnPinningPosition) => boolean
    getLeftLeafColumns: () => Column<TData>[]
    getRightLeafColumns: () => Column<TData>[]
    getCenterLeafColumns: () => Column<TData>[]
}
