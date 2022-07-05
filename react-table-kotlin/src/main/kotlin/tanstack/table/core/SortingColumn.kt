// Automatically generated - do not modify!

package tanstack.table.core

external interface SortingColumn<TData : RowData> {
    var getAutoSortingFn: () -> SortingFn<TData>
    var getAutoSortDir: () -> SortDirection
    var getSortingFn: () -> SortingFn<TData>
    var getNextSortingOrder: () -> SortDirection | false
    var getCanSort: () -> Boolean
    var getCanMultiSort: () -> Boolean
    var getSortIndex: () -> Number
    var getIsSorted: () -> false | SortDirection
    var clearSorting: () -> Unit
    var toggleSorting: (desc?: boolean, isMulti ?: boolean) -> Unit
    var getToggleSortingHandler: () -> undefined | ((event: unknown) -> Unit)
}
