// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreInstance<TData : RowData> {
    initialState: TableState
    reset: () => void
    options: RequiredKeys<TableOptionsResolved<TData>, 'state'>
    setOptions: (newOptions: Updater<TableOptionsResolved<TData>>) => void
    getState: () => TableState
    setState: (updater: Updater<TableState>) => void
    getCoreRowModel: () => RowModel<TData>
    getRowModel: () => RowModel<TData>
    getRow: (id: string) => Row<TData>
    getAllColumns: () => Column<TData>[]
    getAllFlatColumns: () => Column<TData>[]
    getAllLeafColumns: () => Column<TData>[]
    getColumn: (columnId: string) => Column<TData>
}
