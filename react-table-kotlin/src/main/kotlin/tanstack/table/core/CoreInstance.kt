// Automatically generated - do not modify!

package tanstack.table.core

import kotlinx.js.ReadonlyArray

external interface CoreInstance<TData : RowData> {
    var initialState: TableState
    var reset: () -> void
    var options: RequiredKeys<TableOptionsResolved<TData>, 'state'>
    var setOptions: (newOptions: Updater<TableOptionsResolved<TData>>) -> void
    var getState: () -> TableState
    var setState: (updater: Updater<TableState>) -> void
    var getCoreRowModel: () -> RowModel<TData>
    var getRowModel: () -> RowModel<TData>
    var getRow: (id: string) -> Row<TData>
    var getAllColumns: () -> ReadonlyArray<Column<TData>>
    var getAllFlatColumns: () -> ReadonlyArray<Column<TData>>
    var getAllLeafColumns: () -> ReadonlyArray<Column<TData>>
    var getColumn: (columnId: string) -> Column<TData>
}
