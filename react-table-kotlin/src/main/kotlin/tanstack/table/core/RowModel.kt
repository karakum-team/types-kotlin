// Automatically generated - do not modify!

package tanstack.table.core

external interface RowModel<TData extends RowData> = {
    rows: Row<TData>[]
    flatRows: Row<TData>[]
    rowsById: Record<string, Row<TData>>
}
