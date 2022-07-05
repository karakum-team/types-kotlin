// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnPinningInstance<TData : RowData> {
    var setColumnPinning: (updater: Updater<ColumnPinningState>) => void
    var resetColumnPinning: (defaultState?: boolean) => void
    var getIsSomeColumnsPinned: (position?: ColumnPinningPosition) => boolean
    var getLeftLeafColumns: () => Column<TData>[]
    var getRightLeafColumns: () => Column<TData>[]
    var getCenterLeafColumns: () => Column<TData>[]
}
