// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnSizingColumn = {
    getSize: () => number
    getStart: (position ?: ColumnPinningPosition) => number
    getCanResize: () => boolean
    getIsResizing: () => boolean
    resetSize: () => void
}
