// Automatically generated - do not modify!

package tanstack.table.core

type SortingInstance<TData extends RowData> = {
    setSorting: (updater: Updater<SortingState>) => void
    resetSorting: (defaultState ?: boolean) => void
    getPreSortedRowModel: () => RowModel<TData>
    getSortedRowModel: () => RowModel<TData>
    _getSortedRowModel ?: () => RowModel<TData>
}
