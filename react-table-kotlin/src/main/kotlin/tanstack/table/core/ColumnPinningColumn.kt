// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnPinningColumn {
    var getCanPin: () => boolean
    var getPinnedIndex: () => number
    var getIsPinned: () => ColumnPinningPosition
    var pin: (position: ColumnPinningPosition) => void
}
