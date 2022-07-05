// Automatically generated - do not modify!

package tanstack.table.core

external interface ColumnSizingInstance {
    setColumnSizing: (updater: Updater<ColumnSizingState>) => void
    setColumnSizingInfo: (updater: Updater<ColumnSizingInfoState>) => void
    resetColumnSizing: (defaultState?: boolean) => void
    resetHeaderSizeInfo: (defaultState?: boolean) => void
    getTotalSize: () => number
    getLeftTotalSize: () => number
    getCenterTotalSize: () => number
    getRightTotalSize: () => number
}
