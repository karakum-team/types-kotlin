// Automatically generated - do not modify!

package tanstack.table.core

external interface SortingInstance<TData : RowData> {
    var setSorting: (updater: Updater<SortingState>) -> Unit
    var resetSorting: (defaultState?: boolean) -> Unit
    var getPreSortedRowModel: () -> RowModel<TData>
    var getSortedRowModel: () -> RowModel<TData>
}
