// Automatically generated - do not modify!

package tanstack.table.core

external interface GroupingColumnDef<TData : RowData> {
    var aggregationFn?: AggregationFnOption<TData>
    var aggregatedCell?: ColumnDefTemplate<ReturnType<Cell<TData>['getContext']>>
    var enableGrouping?: boolean
}
