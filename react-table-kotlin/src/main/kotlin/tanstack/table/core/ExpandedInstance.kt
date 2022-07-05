// Automatically generated - do not modify!

package tanstack.table.core

external interface ExpandedInstance<TData : RowData> {
    var setExpanded: (updater: Updater<ExpandedState>) -> Unit
    var toggleAllRowsExpanded: (expanded?: boolean) -> Unit
    var resetExpanded: (defaultState?: boolean) -> Unit
    var getCanSomeRowsExpand: () -> Boolean
    var getToggleAllRowsExpandedHandler: () -> (event: unknown) -> Unit
    var getIsSomeRowsExpanded: () -> Boolean
    var getIsAllRowsExpanded: () -> Boolean
    var getExpandedDepth: () -> Number
    var getExpandedRowModel: () -> RowModel<TData>
    var getPreExpandedRowModel: () -> RowModel<TData>
}
