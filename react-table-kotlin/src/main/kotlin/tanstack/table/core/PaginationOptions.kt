// Automatically generated - do not modify!

package tanstack.table.core

external interface PaginationOptions {
    var pageCount?: number
    var manualPagination?: boolean
    var onPaginationChange?: OnChangeFn<PaginationState>
    var autoResetPageIndex?: boolean
    var getPaginationRowModel?: (table: Table<any>) -> () -> RowModel<any>
}
