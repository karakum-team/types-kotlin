// Automatically generated - do not modify!

package tanstack.table.core

external interface ExpandedOptions<TData : RowData> {
    manualExpanding?: boolean
    onExpandedChange?: OnChangeFn<ExpandedState>
    autoResetExpanded?: boolean
    enableExpanding?: boolean
    getExpandedRowModel?: (table: Table<any>) => () => RowModel<any>
    getIsRowExpanded?: (row: Row<TData>) => boolean
    getRowCanExpand?: (row: Row<TData>) => boolean
    paginateExpandedRows?: boolean
}
