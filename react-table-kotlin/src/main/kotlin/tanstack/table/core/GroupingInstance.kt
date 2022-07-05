// Automatically generated - do not modify!

package tanstack.table.core

external interface GroupingInstance<TData : RowData> {
    setGrouping: (updater: Updater<GroupingState>) => void
    resetGrouping: (defaultState?: boolean) => void
    getPreGroupedRowModel: () => RowModel<TData>
    getGroupedRowModel: () => RowModel<TData>
    _getGroupedRowModel?: () => RowModel<TData>
}
