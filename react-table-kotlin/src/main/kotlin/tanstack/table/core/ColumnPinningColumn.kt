// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnPinningColumn = {
    getCanPin: () => boolean
    getPinnedIndex: () => number
    getIsPinned: () => ColumnPinningPosition
    pin: (position: ColumnPinningPosition) => void
}
