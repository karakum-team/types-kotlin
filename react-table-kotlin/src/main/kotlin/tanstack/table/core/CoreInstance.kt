// Automatically generated - do not modify!

package tanstack.table.core

type CoreInstance<TData extends RowData> = {
    initialState: TableState
    reset: () => void
    options: RequiredKeys<TableOptionsResolved<TData>, 'state'>
    setOptions: (newOptions: Updater<TableOptionsResolved<TData>>) => void
    getState: () => TableState
    setState: (updater: Updater<TableState>) => void
    _features: readonly TableFeature[]
    _queue: (cb: () => void) => void
    _getRowId: (_: TData, index: number, parent ?: Row<TData>) => string
    getCoreRowModel: () => RowModel<TData>
    _getCoreRowModel ?: () => RowModel<TData>
    getRowModel: () => RowModel<TData>
    getRow: (id: string) => Row<TData>
    _getDefaultColumnDef: () => Partial<ColumnDef<TData>>
    _getColumnDefs: () => ColumnDef<TData>[]
    _getAllFlatColumnsById: () => Record<string, Column<TData>>
    getAllColumns: () => Column<TData>[]
    getAllFlatColumns: () => Column<TData>[]
    getAllLeafColumns: () => Column<TData>[]
    getColumn: (columnId: string) => Column<TData>
}
