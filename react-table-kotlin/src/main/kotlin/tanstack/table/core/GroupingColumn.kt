// Automatically generated - do not modify!

package tanstack.table.core

external interface GroupingColumn<TData : RowData> {
    var getCanGroup: () -> boolean
    var getIsGrouped: () -> boolean
    var getGroupedIndex: () -> number
    var toggleGrouping: () -> void
    var getToggleGroupingHandler: () -> () -> void
    var getAutoAggregationFn: () -> AggregationFn<TData>
    var getAggregationFn: () -> AggregationFn<TData>
}
