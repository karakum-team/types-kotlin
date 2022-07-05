// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnOrderInstance<TData extends RowData> = {
    setColumnOrder: (updater: Updater<ColumnOrderState>) => void
    resetColumnOrder: (defaultState ?: boolean) => void
    _getOrderColumnsFn: () => (columns: Column<TData>[]) => Column<TData>[]
}
