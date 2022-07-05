// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreColumn<TData : RowData> {
    var id: String
    var depth: Number
    var accessorFn: AccessorFn<TData>?
    var columnDef: ColumnDef<TData>
    var columns: Column<TData>[]
    var parent: Column<TData>?
    var getFlatColumns: () -> Column<TData>[]
    var getLeafColumns: () -> Column<TData>[]
}
