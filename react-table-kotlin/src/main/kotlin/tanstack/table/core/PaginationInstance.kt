// Automatically generated - do not modify!

package tanstack.table.core

external interface PaginationInstance<TData : RowData> {
    var setPagination: (updater: Updater<PaginationState>) -> Unit
    var resetPagination: (defaultState?: boolean) -> Unit
    var setPageIndex: (updater: Updater<number>) -> Unit
    var resetPageIndex: (defaultState?: boolean) -> Unit
    var setPageSize: (updater: Updater<number>) -> Unit
    var resetPageSize: (defaultState?: boolean) -> Unit
    var setPageCount: (updater: Updater<number>) -> Unit
    var getPageOptions: () -> number[]
    var getCanPreviousPage: () -> Boolean
    var getCanNextPage: () -> Boolean
    var previousPage: () -> Unit
    var nextPage: () -> Unit
    var getPrePaginationRowModel: () -> RowModel<TData>
    var getPaginationRowModel: () -> RowModel<TData>
    var getPageCount: () -> Number
}
