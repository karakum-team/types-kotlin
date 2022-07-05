// Automatically generated - do not modify!

package tanstack.table.core

external interface FiltersColumn<TData : RowData> {
    getAutoFilterFn: () => FilterFn<TData> | undefined
    getFilterFn: () => FilterFn<TData> | undefined
    setFilterValue: (updater: Updater<any>) => void
    getCanFilter: () => boolean
    getCanGlobalFilter: () => boolean
    getFacetedRowModel: () => RowModel<TData>
    getIsFiltered: () => boolean
    getFilterValue: () => unknown
    getFilterIndex: () => number
    getFacetedUniqueValues: () => Map<any, number>
    getFacetedMinMaxValues: () => undefined | [number, number]
}
