// Automatically generated - do not modify!

package tanstack.table.core

sealed external interface FilterFnOption<TData : RowData> /* 'auto' | BuiltInFilterFn | FilterFn<TData> */

inline fun <TData : RowData> FilterFnOption(
    source: String, /* 'auto' */
): FilterFnOption<TData> =
    source.unsafeCast<FilterFnOption<TData>>()

inline fun <TData : RowData> FilterFnOption(
    source: FilterFn<TData>,
): FilterFnOption<TData> =
    source.unsafeCast<FilterFnOption<TData>>()
