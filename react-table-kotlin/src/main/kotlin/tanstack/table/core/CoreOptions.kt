// Automatically generated - do not modify!

package tanstack.table.core

import kotlinx.js.ReadonlyArray

external interface CoreOptions<TData : RowData> {
    var data: ReadonlyArray<TData>
    var state: Partial<TableState>
    var onStateChange: (updater: Updater<TableState>) -> Unit
    var debugAll: Boolean?
    var debugTable: Boolean?
    var debugHeaders: Boolean?
    var debugColumns: Boolean?
    var debugRows: Boolean?
    var initialState: InitialTableState?
    var autoResetAll: Boolean?
    var mergeOptions: ((defaultOptions: TableOptions<TData>, options: Partial<TableOptions<TData>>) -> TableOptions<TData>)?
    var meta: Any?
    var getCoreRowModel: (table: Table<any>) -> () -> RowModel<any>
    var getSubRows: ((originalRow: TData, index: number) -> undefined | TData[])?
    var getRowId: ((originalRow: TData, index: number, parent?: Row<TData>) -> string)?
    var columns: ReadonlyArray<ColumnDef<TData>>
    var defaultColumn: Partial<ColumnDef<TData>>?
    var renderFallbackValue: Any
}
