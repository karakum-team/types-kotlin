// Automatically generated - do not modify!

package tanstack.table.core

import tanstack.table.core.VisibilityColumn as ColumnVisibilityColumn

external interface ResolvedColumnFilter<TData : RowData>{
var id: String
var resolvedValue: Any
var filterFn: FilterFn<TData>
}
