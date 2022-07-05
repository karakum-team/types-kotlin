// Automatically generated - do not modify!

package tanstack.table.core

external interface RowSelectionOptions<TData : RowData> {
    enableRowSelection?: boolean | ((row: Row<TData>) => boolean)
    enableMultiRowSelection?: boolean | ((row: Row<TData>) => boolean)
    enableSubRowSelection?: boolean | ((row: Row<TData>) => boolean)
    onRowSelectionChange?: OnChangeFn<RowSelectionState>
}
