// Automatically generated - do not modify!

package tanstack.table.core

external interface RowSelectionInstance<TData extends RowData> = {
    getToggleAllRowsSelectedHandler: () => (event: unknown) => void
    getToggleAllPageRowsSelectedHandler: () => (event: unknown) => void
    setRowSelection: (updater: Updater<RowSelectionState>) => void
    resetRowSelection: (defaultState ?: boolean) => void
    getIsAllRowsSelected: () => boolean
    getIsAllPageRowsSelected: () => boolean
    getIsSomeRowsSelected: () => boolean
    getIsSomePageRowsSelected: () => boolean
    toggleAllRowsSelected: (value ?: boolean) => void
    toggleAllPageRowsSelected: (value ?: boolean) => void
    getPreSelectedRowModel: () => RowModel<TData>
    getSelectedRowModel: () => RowModel<TData>
    getFilteredSelectedRowModel: () => RowModel<TData>
    getGroupedSelectedRowModel: () => RowModel<TData>
}
