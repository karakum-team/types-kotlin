// Automatically generated - do not modify!

package tanstack.table.core

external interface FiltersColumn<TData : RowData> {
    var getAutoFilterFn: () -> FilterFn<TData>
    var getFilterFn: () -> FilterFn<TData>
    var setFilterValue: (updater: Updater<any>) -> void
    var getCanFilter: () -> boolean
    var getCanGlobalFilter: () -> boolean
    var getFacetedRowModel: () -> RowModel<TData>
    var getIsFiltered: () -> boolean
    var getFilterValue: () -> unknown
    var getFilterIndex: () -> number
    var getFacetedUniqueValues: () -> Map<any, number>
    var getFacetedMinMaxValues: () -> undefined | [number, number]
}
