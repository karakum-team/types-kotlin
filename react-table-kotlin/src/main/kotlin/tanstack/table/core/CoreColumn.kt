// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreColumn<TData extends RowData> = {
    id: string
    depth: number
    accessorFn ?: AccessorFn<TData>
    columnDef: ColumnDef<TData>
    columns: Column<TData>[]
    parent ?: Column<TData>
    getFlatColumns: () => Column<TData>[]
    getLeafColumns: () => Column<TData>[]
}
