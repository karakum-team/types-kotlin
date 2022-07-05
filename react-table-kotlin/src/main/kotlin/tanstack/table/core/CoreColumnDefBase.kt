// Automatically generated - do not modify!

package tanstack.table.core

type CoreColumnDefBase<TData extends RowData> = {
    columns ?: ColumnDef<TData>[]
    header ?: ColumnDefTemplate < ReturnType < CoreHeader<TData>['getContext'] > >
    footer ?: ColumnDefTemplate < ReturnType < CoreHeader<TData>['getContext'] > >
    cell ?: ColumnDefTemplate < ReturnType < CoreCell<TData>['getContext'] > >
    meta ?: unknown
}
