// Automatically generated - do not modify!

package tanstack.table.core

import kotlinx.js.ReadonlyArray

external interface CoreColumnDefBase<TData : RowData> {
    var columns: ReadonlyArray<ColumnDef<TData>>?
    var header: ColumnDefTemplate<ReturnType<CoreHeader<TData>['getContext']>>?
    var footer: ColumnDefTemplate<ReturnType<CoreHeader<TData>['getContext']>>?
    var cell: ColumnDefTemplate<ReturnType<CoreCell<TData>['getContext']>>?
    var meta: Any?
}
