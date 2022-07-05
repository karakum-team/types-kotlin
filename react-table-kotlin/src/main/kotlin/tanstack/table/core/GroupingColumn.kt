// Automatically generated - do not modify!

package tanstack.table.core

type GroupingColumn<TData extends RowData> = {
    getCanGroup: () => boolean
    getIsGrouped: () => boolean
    getGroupedIndex: () => number
    toggleGrouping: () => void
    getToggleGroupingHandler: () => () => void
    getAutoAggregationFn: () => AggregationFn<TData> | undefined
    getAggregationFn: () => AggregationFn<TData> | undefined
}
