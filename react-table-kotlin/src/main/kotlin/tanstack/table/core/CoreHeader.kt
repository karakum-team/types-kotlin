// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreHeader<TData : RowData> {
    id: string
    index: number
    depth: number
    column: Column<TData>
    headerGroup: HeaderGroup<TData>
    subHeaders: Header<TData>[]
    colSpan: number
    rowSpan: number
    getLeafHeaders: () => Header<TData>[]
    isPlaceholder: boolean
    placeholderId?: string
    getContext: () =>
    {
        table: Table<TData>
        header: Header<TData>
        column: Column<TData>
    }
}
