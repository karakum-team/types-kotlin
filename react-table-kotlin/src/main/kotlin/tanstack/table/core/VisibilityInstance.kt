// Automatically generated - do not modify!

package tanstack.table.core

external interface VisibilityInstance<TData : RowData> {
    getVisibleFlatColumns: () => Column<TData>[]
    getVisibleLeafColumns: () => Column<TData>[]
    getLeftVisibleLeafColumns: () => Column<TData>[]
    getRightVisibleLeafColumns: () => Column<TData>[]
    getCenterVisibleLeafColumns: () => Column<TData>[]
    setColumnVisibility: (updater: Updater<VisibilityState>) => void
    resetColumnVisibility: (defaultState?: boolean) => void
    toggleAllColumnsVisible: (value ?: boolean) => void
    getIsAllColumnsVisible: () => boolean
    getIsSomeColumnsVisible: () => boolean
    getToggleAllColumnsVisibilityHandler: () => (event: unknown) => void
}
