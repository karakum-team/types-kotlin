// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnSizingColumn {
    var getSize: () -> number
    var getStart: (position?: ColumnPinningPosition) -> number
    var getCanResize: () -> boolean
    var getIsResizing: () -> boolean
    var resetSize: () -> void
}
