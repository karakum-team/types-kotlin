// Automatically generated - do not modify!

package tanstack.table.core

external interface FiltersOptions<TData : RowData> {
    var enableFilters: Boolean?
    var manualFiltering: Boolean?
    var filterFromLeafRows: Boolean?
    var getFilteredRowModel: ((table: Table<any>) -> () -> RowModel<any>)?
    var onColumnFiltersChange: OnChangeFn<ColumnFiltersState>?
    var enableColumnFilters: Boolean?
    var globalFilterFn: FilterFnOption<TData>?
    var onGlobalFilterChange: OnChangeFn<any>?
    var enableGlobalFilter: Boolean?
    var getColumnCanGlobalFilter: ((column: Column<TData>) -> boolean)?
    var getFacetedRowModel: ((table: Table<TData>, columnId: string) -> () -> RowModel<TData>)?
    var getFacetedUniqueValues: ((table: Table<TData>, columnId: string) -> () -> Map<any, number>)?
    var getFacetedMinMaxValues: ((table: Table<TData>, columnId: string) -> () -> undefined | [number, number])?
}
