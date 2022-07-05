// Automatically generated - do not modify!

package tanstack.table.core

external interface SortingInstance<TData : RowData> {
    var setSorting: (updater: Updater<SortingState>) => void
    var resetSorting: (defaultState?: boolean) => void
    var getPreSortedRowModel: () => RowModel<TData>
    var getSortedRowModel: () => RowModel<TData>
}
