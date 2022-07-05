// Automatically generated - do not modify!

package tanstack.table.core

type FiltersColumn<TData extends RowData> = {
    getAutoFilterFn: () => FilterFn<TData> | undefined
    getFilterFn: () => FilterFn<TData> | undefined
    setFilterValue: (updater: Updater<any>) => void
    getCanFilter: () => boolean
    getCanGlobalFilter: () => boolean
    getFacetedRowModel: () => RowModel<TData>
    _getFacetedRowModel ?: () => RowModel<TData>
    getIsFiltered: () => boolean
    getFilterValue: () => unknown
    getFilterIndex: () => number
    getFacetedUniqueValues: () => Map<any, number>
    _getFacetedUniqueValues ?: () => Map<any, number>
    getFacetedMinMaxValues: () => undefined | [number, number]
    _getFacetedMinMaxValues ?: () => undefined | [number, number]
}
