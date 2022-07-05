// Automatically generated - do not modify!

package tanstack.table.core

external interface GroupingColumn<TData : RowData> {
    getCanGroup: () => boolean
    getIsGrouped: () => boolean
    getGroupedIndex: () => number
    toggleGrouping: () => void
    getToggleGroupingHandler: () => () => void
    getAutoAggregationFn: () => AggregationFn<TData> | undefined
    getAggregationFn: () => AggregationFn<TData> | undefined
}
