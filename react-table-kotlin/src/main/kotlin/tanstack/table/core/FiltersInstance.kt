// Automatically generated - do not modify!

package tanstack.table.core

external interface FiltersInstance<TData : RowData> {
    var setColumnFilters: (updater: Updater<ColumnFiltersState>) => void
    var resetColumnFilters: (defaultState?: boolean) => void
    var getPreFilteredRowModel: () => RowModel<TData>
    var getFilteredRowModel: () => RowModel<TData>
    var setGlobalFilter: (updater: Updater<any>) => void
    var resetGlobalFilter: (defaultState?: boolean) => void
    var getGlobalAutoFilterFn: () => FilterFn<TData> | undefined
    var getGlobalFilterFn: () => FilterFn<TData> | undefined
    var getGlobalFacetedRowModel: () => RowModel<TData>
    var getGlobalFacetedUniqueValues: () => Map<any, number>
    var getGlobalFacetedMinMaxValues: () => undefined | [number, number]
}
