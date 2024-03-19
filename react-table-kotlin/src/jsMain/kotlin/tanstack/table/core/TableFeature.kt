// Automatically generated - do not modify!

package tanstack.table.core

external interface TableFeature<TData : RowData = any>{
    var createCell: ((cell: Cell<TData, unknown>, column: Column<TData>, row: Row<TData>, table: Table<TData>) -> Unit)?
    var createColumn: ((column: Column<TData, *>, table: Table<TData>) -> Unit)?
    var createHeader: ((header: Header<TData, unknown>, table: Table<TData>) -> Unit)?
    var createRow: ((row: Row<TData>, table: Table<TData>) -> Unit)?
    var createTable: ((table: Table<TData>) -> Unit)?
    var getDefaultColumnDef: (() -> Partial<ColumnDef<TData, unknown>>)?
    var getDefaultOptions: ((table: Table<TData>) -> Partial<TableOptionsResolved<TData>>)?
    var getInitialState: ((initialState: InitialTableState?) -> Partial<TableState>)?
}
