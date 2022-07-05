// Automatically generated - do not modify!

package tanstack.table.core

external interface CoreRow<TData : RowData> {
    id: string
    index: number
    original?: TData
    depth: number
    getValue: (columnId: string) => any
    renderValue: (columnId: string) => unknown
    subRows: Row<TData>[]
    getLeafRows: () => Row<TData>[]
    originalSubRows?: TData[]
    getAllCells: () => Cell<TData>[]
}
