// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreHeader<TData : RowData> {
    var id: String
    var index: Number
    var depth: Number
    var column: Column<TData>
    var headerGroup: HeaderGroup<TData>
    var subHeaders: Header<TData>[]
    var colSpan: Number
    var rowSpan: Number
    var getLeafHeaders: () -> Header<TData>[]
    var isPlaceholder: Boolean
    var placeholderId: String?
    var getContext: () ->
    {
        var table: Table<TData>
        var header: Header<TData>
        var column: Column<TData>
        var
    }:
}
}
