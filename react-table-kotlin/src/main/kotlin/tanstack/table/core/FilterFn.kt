// Automatically generated - do not modify!

package tanstack.table.core

external interface FilterFn<TData : RowData> {
    var (row: Row<TData>, columnId: String, filterValue: Any, addMeta: (meta: Any) -> Unit): Boolean
    var resolveFilterValue: TransformFilterValueFn<TData>?
    var autoRemove: ColumnFilterAutoRemoveTestFn<TData>?
}
