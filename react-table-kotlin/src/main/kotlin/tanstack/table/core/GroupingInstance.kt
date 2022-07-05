// Automatically generated - do not modify!

package tanstack.table.core

external interface GroupingInstance<TData : RowData> {
    var setGrouping: (updater: Updater<GroupingState>) -> void
    var resetGrouping: (defaultState?: boolean) -> void
    var getPreGroupedRowModel: () -> RowModel<TData>
    var getGroupedRowModel: () -> RowModel<TData>
}
