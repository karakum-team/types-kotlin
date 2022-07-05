// Automatically generated - do not modify!

package tanstack.table.core

external interface PaginationOptions = {
    pageCount ?: number
    manualPagination ?: boolean
    onPaginationChange ?: OnChangeFn<PaginationState>
    autoResetPageIndex ?: boolean
    getPaginationRowModel ?: (table: Table<any>) => () => RowModel<any>
}
