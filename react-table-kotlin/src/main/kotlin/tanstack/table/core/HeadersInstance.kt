// Automatically generated - do not modify!

package tanstack.table.core

external interface HeadersInstance<TData : RowData> {
    var getHeaderGroups: () => HeaderGroup<TData>[]
    var getLeftHeaderGroups: () => HeaderGroup<TData>[]
    var getCenterHeaderGroups: () => HeaderGroup<TData>[]
    var getRightHeaderGroups: () => HeaderGroup<TData>[]
    var getFooterGroups: () => HeaderGroup<TData>[]
    var getLeftFooterGroups: () => HeaderGroup<TData>[]
    var getCenterFooterGroups: () => HeaderGroup<TData>[]
    var getRightFooterGroups: () => HeaderGroup<TData>[]
    var getFlatHeaders: () => Header<TData>[]
    var getLeftFlatHeaders: () => Header<TData>[]
    var getCenterFlatHeaders: () => Header<TData>[]
    var getRightFlatHeaders: () => Header<TData>[]
    var getLeafHeaders: () => Header<TData>[]
    var getLeftLeafHeaders: () => Header<TData>[]
    var getCenterLeafHeaders: () => Header<TData>[]
    var getRightLeafHeaders: () => Header<TData>[]
}
