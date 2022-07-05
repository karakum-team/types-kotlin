// Automatically generated - do not modify!

package tanstack.table.core

external interface ExpandedInstance<TData : RowData> {
    var setExpanded: (updater: Updater<ExpandedState>) -> void
    var toggleAllRowsExpanded: (expanded?: boolean) -> void
    var resetExpanded: (defaultState?: boolean) -> void
    var getCanSomeRowsExpand: () -> Boolean
    var getToggleAllRowsExpandedHandler: () -> (event: unknown) -> void
    var getIsSomeRowsExpanded: () -> Boolean
    var getIsAllRowsExpanded: () -> Boolean
    var getExpandedDepth: () -> Number
    var getExpandedRowModel: () -> RowModel<TData>
    var getPreExpandedRowModel: () -> RowModel<TData>
}
