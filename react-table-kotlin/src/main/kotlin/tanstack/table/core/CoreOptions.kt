// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreOptions<TData : RowData> {
    data : TData[]
    state: Partial<TableState>
    onStateChange: (updater: Updater<TableState>) => void
    debugAll?: boolean
    debugTable?: boolean
    debugHeaders?: boolean
    debugColumns?: boolean
    debugRows?: boolean
    initialState?: InitialTableState
    autoResetAll?: boolean
    mergeOptions?: (defaultOptions: TableOptions<TData>, options: Partial<TableOptions<TData>>) => TableOptions<TData>
    meta?: unknown
    getCoreRowModel: (table: Table<any>) => () => RowModel<any>
    getSubRows?: (originalRow: TData, index: number) => undefined | TData[]
    getRowId?: (originalRow: TData, index: number, parent?: Row<TData>) => string
    columns: ColumnDef<TData>[]
    defaultColumn?: Partial<ColumnDef<TData>>
    renderFallbackValue: any
}
