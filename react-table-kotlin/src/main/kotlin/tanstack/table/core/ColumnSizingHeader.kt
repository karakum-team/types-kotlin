// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnSizingHeader {
    var getSize: () -> Number
    var getStart: (position?: ColumnPinningPosition) -> number
    var getResizeHandler: () -> (event: unknown) -> void
}
