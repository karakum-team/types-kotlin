// Automatically generated - do not modify!

package tanstack.table.core

external interface FiltersColumn<TData : RowData> {
    var getAutoFilterFn: () -> FilterFn<TData>
    var getFilterFn: () -> FilterFn<TData>
    var setFilterValue: (updater: Updater<*>) -> Unit
    var getCanFilter: () -> Boolean
    var getCanGlobalFilter: () -> Boolean
    var getFacetedRowModel: () -> RowModel<TData>
    var getIsFiltered: () -> Boolean
    var getFilterValue: () -> unknown
    var getFilterIndex: () -> Number
    var getFacetedUniqueValues: () -> Map<any, number>
    var getFacetedMinMaxValues: () -> undefined | [number, number]
}
