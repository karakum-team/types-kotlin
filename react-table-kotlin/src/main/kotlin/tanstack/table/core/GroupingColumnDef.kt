// Automatically generated - do not modify!

package tanstack.table.core

type GroupingColumnDef<TData extends RowData> = {
    aggregationFn ?: AggregationFnOption<TData>
    aggregatedCell ?: ColumnDefTemplate < ReturnType < Cell<TData>['getContext'] > >
    enableGrouping ?: boolean
}
