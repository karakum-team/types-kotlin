// Automatically generated - do not modify!

package tanstack.table.core

sealed external interface SortingFnOption<TData : RowData> /* 'auto' | BuiltInSortingFn | SortingFn<TData> */

inline fun <TData : RowData> SortingFnOption(
    source: String, /* 'auto' */
): SortingFnOption<TData> =
    source.unsafeCast<SortingFnOption<TData>>()

inline fun <TData : RowData> SortingFnOption(
    source: SortingFn<TData>,
): SortingFnOption<TData> =
    source.unsafeCast<SortingFnOption<TData>>()
