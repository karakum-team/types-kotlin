// Automatically generated - do not modify!

package tanstack.table.core

external interface FiltersInstance<TData : RowData> {
    var setColumnFilters: (updater: Updater<ColumnFiltersState>) -> Unit
    var resetColumnFilters: (defaultState?: boolean) -> Unit
    var getPreFilteredRowModel: () -> RowModel<TData>
    var getFilteredRowModel: () -> RowModel<TData>
    var setGlobalFilter: (updater: Updater<any>) -> Unit
    var resetGlobalFilter: (defaultState?: boolean) -> Unit
    var getGlobalAutoFilterFn: () -> FilterFn<TData>
    var getGlobalFilterFn: () -> FilterFn<TData>
    var getGlobalFacetedRowModel: () -> RowModel<TData>
    var getGlobalFacetedUniqueValues: () -> Map<any, number>
    var getGlobalFacetedMinMaxValues: () -> undefined | [number, number]
}
