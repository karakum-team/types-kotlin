// Automatically generated - do not modify!

package tanstack.table.core

import kotlinx.js.Record

external fun <TData : RowData> isSubRowSelected(
    row: Row<TData>,
    selection: Record<string, boolean>,
    table: Table<TData>,
): boolean | 'some' | 'all'
