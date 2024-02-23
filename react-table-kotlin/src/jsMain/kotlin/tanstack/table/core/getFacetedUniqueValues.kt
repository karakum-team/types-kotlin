// Automatically generated - do not modify!

@file:JsModule("@tanstack/table-core")

package tanstack.table.core

import js.collections.JsMap
import tanstack.table.core.VisibilityColumn as ColumnVisibilityColumn

external fun <TData : RowData> getFacetedUniqueValues(): (table: Table<TData>, columnId: String) -> () -> JsMap<Any, Int>;
