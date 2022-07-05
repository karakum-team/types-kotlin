// Automatically generated - do not modify!

package tanstack.table.core

external interface VisibilityInstance<TData : RowData> {
    var getVisibleFlatColumns: () -> ReadonlyArray<Column<TData>>
    var getVisibleLeafColumns: () -> ReadonlyArray<Column<TData>>
    var getLeftVisibleLeafColumns: () -> ReadonlyArray<Column<TData>>
    var getRightVisibleLeafColumns: () -> ReadonlyArray<Column<TData>>
    var getCenterVisibleLeafColumns: () -> ReadonlyArray<Column<TData>>
    var setColumnVisibility: (updater: Updater<VisibilityState>) -> void
    var resetColumnVisibility: (defaultState?: boolean) -> void
    var toggleAllColumnsVisible: (value?: boolean) -> void
    var getIsAllColumnsVisible: () -> Boolean
    var getIsSomeColumnsVisible: () -> Boolean
    var getToggleAllColumnsVisibilityHandler: () -> (event: unknown) -> void
}
