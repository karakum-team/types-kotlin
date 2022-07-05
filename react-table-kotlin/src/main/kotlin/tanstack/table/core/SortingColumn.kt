// Automatically generated - do not modify!

package tanstack.table.core

external interface SortingColumn<TData : RowData> {
    getAutoSortingFn: () => SortingFn<TData>
    getAutoSortDir: () => SortDirection
    getSortingFn: () => SortingFn<TData>
    getNextSortingOrder: () => SortDirection | false
    getCanSort: () => boolean
    getCanMultiSort: () => boolean
    getSortIndex: () => number
    getIsSorted: () => false | SortDirection
    clearSorting: () => void
    toggleSorting: (desc?: boolean, isMulti?: boolean) => void
    getToggleSortingHandler: () => undefined | ((event: unknown) => void)
}
