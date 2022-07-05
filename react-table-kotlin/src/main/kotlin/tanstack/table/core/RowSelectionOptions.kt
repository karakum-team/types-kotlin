// Automatically generated - do not modify!

package tanstack.table.core

external interface RowSelectionOptions<TData : RowData> {
    var enableRowSelection?: boolean | ((row: Row<TData>) => boolean)
    var enableMultiRowSelection?: boolean | ((row: Row<TData>) => boolean)
    var enableSubRowSelection?: boolean | ((row: Row<TData>) => boolean)
    var onRowSelectionChange?: OnChangeFn<RowSelectionState>
}
