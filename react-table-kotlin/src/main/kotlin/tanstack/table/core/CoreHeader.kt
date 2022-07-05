// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreHeader<TData : RowData> {
    var id: string
    var index: number
    var depth: number
    var column: Column<TData>
    var headerGroup: HeaderGroup<TData>
    var subHeaders: Header<TData>[]
    var colSpan: number
    var rowSpan: number
    var getLeafHeaders: () -> Header<TData>[]
    var isPlaceholder: boolean
    var placeholderId?: string
    var getContext: () ->

    {
        var table: Table<TData>
        var header: Header<TData>
        var column: Column<TData>
        var
    }
}
