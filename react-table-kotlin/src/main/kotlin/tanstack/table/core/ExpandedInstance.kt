// Automatically generated - do not modify!

package tanstack.table.core

external interface ExpandedInstance<TData : RowData> {
    var setExpanded: (updater: Updater<ExpandedState>) -> void
    var toggleAllRowsExpanded: (expanded?: boolean) -> void
    var resetExpanded: (defaultState?: boolean) -> void
    var getCanSomeRowsExpand: () -> boolean
    var getToggleAllRowsExpandedHandler: () -> (event: unknown) -> void
    var getIsSomeRowsExpanded: () -> boolean
    var getIsAllRowsExpanded: () -> boolean
    var getExpandedDepth: () -> number
    var getExpandedRowModel: () -> RowModel<TData>
    var getPreExpandedRowModel: () -> RowModel<TData>
}
