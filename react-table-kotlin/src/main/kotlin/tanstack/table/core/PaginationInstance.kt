// Automatically generated - do not modify!

package tanstack.table.core

external interface PaginationInstance<TData : RowData> {
    var setPagination: (updater: Updater<PaginationState>) -> void
    var resetPagination: (defaultState?: boolean) -> void
    var setPageIndex: (updater: Updater<number>) -> void
    var resetPageIndex: (defaultState?: boolean) -> void
    var setPageSize: (updater: Updater<number>) -> void
    var resetPageSize: (defaultState?: boolean) -> void
    var setPageCount: (updater: Updater<number>) -> void
    var getPageOptions: () -> number[]
    var getCanPreviousPage: () -> boolean
    var getCanNextPage: () -> boolean
    var previousPage: () -> void
    var nextPage: () -> void
    var getPrePaginationRowModel: () -> RowModel<TData>
    var getPaginationRowModel: () -> RowModel<TData>
    var getPageCount: () -> number
}
