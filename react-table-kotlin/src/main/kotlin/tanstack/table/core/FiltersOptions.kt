// Automatically generated - do not modify!

package tanstack.table.core

external interface FiltersOptions<TData : RowData> {
    var enableFilters?: boolean
    var manualFiltering?: boolean
    var filterFromLeafRows?: boolean
    var getFilteredRowModel?: (table: Table<any>) -> () -> RowModel<any>
    var onColumnFiltersChange?: OnChangeFn<ColumnFiltersState>
    var enableColumnFilters?: boolean
    var globalFilterFn?: FilterFnOption<TData>
    var onGlobalFilterChange?: OnChangeFn<any>
    var enableGlobalFilter?: boolean
    var getColumnCanGlobalFilter?: (column: Column<TData>) -> boolean
    var getFacetedRowModel?: (table: Table<TData>, columnId: string) -> () -> RowModel<TData>
    var getFacetedUniqueValues?: (table: Table<TData>, columnId: string) -> () -> Map<any, number>
    var getFacetedMinMaxValues?: (table: Table<TData>, columnId: string) -> () -> undefined | [number, number]
}
