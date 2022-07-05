// Automatically generated - do not modify!

package tanstack.table.core

external interface ExpandedOptions<TData : RowData> {
    var manualExpanding: boolean?
    var onExpandedChange: OnChangeFn<ExpandedState>?
    var autoResetExpanded: boolean?
    var enableExpanding: boolean?
    var getExpandedRowModel: ((table: Table<any>) -> () -> RowModel<any>)?
    var getIsRowExpanded: ((row: Row<TData>) -> boolean)?
    var getRowCanExpand: ((row: Row<TData>) -> boolean)?
    var paginateExpandedRows: boolean?
}
