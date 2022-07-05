// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnSizingInstance {
    var setColumnSizing: (updater: Updater<ColumnSizingState>) -> void
    var setColumnSizingInfo: (updater: Updater<ColumnSizingInfoState>) -> void
    var resetColumnSizing: (defaultState?: boolean) -> void
    var resetHeaderSizeInfo: (defaultState?: boolean) -> void
    var getTotalSize: () -> Number
    var getLeftTotalSize: () -> Number
    var getCenterTotalSize: () -> Number
    var getRightTotalSize: () -> Number
}
