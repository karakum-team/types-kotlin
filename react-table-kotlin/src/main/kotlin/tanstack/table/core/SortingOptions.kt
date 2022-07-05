// Automatically generated - do not modify!

package tanstack.table.core

external interface SortingOptions<TData : RowData> {
    manualSorting?: boolean
    onSortingChange?: OnChangeFn<SortingState>
    enableSorting?: boolean
    enableSortingRemoval?: boolean
    enableMultiRemove?: boolean
    enableMultiSort?: boolean
    sortDescFirst?: boolean
    getSortedRowModel?: (table: Table<any>) => () => RowModel<any>
    maxMultiSortColCount?: number
    isMultiSortEvent?: (e: unknown) => boolean
}
