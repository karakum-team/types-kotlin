// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreCell<TData : RowData> {
    var id: String
    var getValue: () -> Any
    var renderValue: () -> unknown
    var row: Row<TData>
    var column: Column<TData>
    var getContext: () ->

    {
        var table: Table<TData>
        var column: Column<TData>
        var row: Row<TData>
        var cell: Cell<TData>
        var getValue: () -> Any
        var renderValue: () -> Any
        var
    }:
}
}
