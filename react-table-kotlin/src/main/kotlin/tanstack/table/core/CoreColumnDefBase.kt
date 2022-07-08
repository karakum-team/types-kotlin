// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreColumnDefBase<TData : RowData, TValue> {
    var columns: ColumnDef<TData>[]?
    var header: ColumnDefTemplate<() -> CoreHeader.Context<TData, TValue>>?
    var footer: ColumnDefTemplate<() -> CoreHeader.Context<TData, TValue>>?
    var cell: ColumnDefTemplate<() -> CoreCell.Context<TData, TValue>>?
    var meta: ColumnMeta?
}
