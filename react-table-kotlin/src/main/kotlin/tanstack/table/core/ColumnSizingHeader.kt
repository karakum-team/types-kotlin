// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnSizingHeader = {
    getSize: () => number
    getStart: (position ?: ColumnPinningPosition) => number
    getResizeHandler: () => (event: unknown) => void
}
