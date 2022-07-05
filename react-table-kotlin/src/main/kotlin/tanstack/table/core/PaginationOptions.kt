// Automatically generated - do not modify!

package tanstack.table.core

external interface PaginationOptions {
    var pageCount: number?
    var manualPagination: Boolean?
    var onPaginationChange: OnChangeFn<PaginationState>?
    var autoResetPageIndex: Boolean?
    var getPaginationRowModel: ((table: Table<any>) -> () -> RowModel<any>)?
}
