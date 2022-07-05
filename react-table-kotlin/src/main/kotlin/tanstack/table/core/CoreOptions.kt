// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreOptions<TData : RowData> {
    var data: TData[]
    var state: Partial<TableState>
    var onStateChange: (updater: Updater<TableState>) -> void
    var debugAll?: boolean
    var debugTable?: boolean
    var debugHeaders?: boolean
    var debugColumns?: boolean
    var debugRows?: boolean
    var initialState?: InitialTableState
    var autoResetAll?: boolean
    var mergeOptions?: (defaultOptions: TableOptions<TData>, options: Partial<TableOptions<TData>>) -> TableOptions<TData>
    var meta?: unknown
    var getCoreRowModel: (table: Table<any>) -> () -> RowModel<any>
    var getSubRows?: (originalRow: TData, index: number) -> undefined | TData[]
    var getRowId?: (originalRow: TData, index: number, parent ?: Row<TData>) -> string
    var columns: ColumnDef<TData>[]
    var defaultColumn?: Partial<ColumnDef<TData>>
    var renderFallbackValue: any
}
