// Automatically generated - do not modify!

package tanstack.table.core

external interface SortingOptions<TData : RowData> {
    var manualSorting?: boolean
    var onSortingChange?: OnChangeFn<SortingState>
    var enableSorting?: boolean
    var enableSortingRemoval?: boolean
    var enableMultiRemove?: boolean
    var enableMultiSort?: boolean
    var sortDescFirst?: boolean
    var getSortedRowModel?: (table: Table<any>) => () => RowModel<any>
    var maxMultiSortColCount?: number
    var isMultiSortEvent?: (e: unknown) => boolean
}
