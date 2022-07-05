// Automatically generated - do not modify!

package tanstack.table.core

external interface SortingInstance<TData : RowData> {
    setSorting: (updater: Updater<SortingState>) => void
    resetSorting: (defaultState?: boolean) => void
    getPreSortedRowModel: () => RowModel<TData>
    getSortedRowModel: () => RowModel<TData>
    _getSortedRowModel?: () => RowModel<TData>
}
