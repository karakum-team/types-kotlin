// Automatically generated - do not modify!

package tanstack.table.core

external interface IdentifiedColumnDef<TData : RowData, TValue> :
    ColumnDefBase<TData, TValue>, {
    id?: string
    header ?: StringOrTemplateHeader<TData, TValue>
}
