// Automatically generated - do not modify!

package tanstack.table.core

sealed external interface AggregationFnOption<TData : RowData> /* 'auto' | BuiltInAggregationFn | AggregationFn<TData> */

inline fun <TData : RowData> AggregationFnOption(
    source: String, /* 'auto' */
): AggregationFnOption<TData> =
    source.unsafeCast<AggregationFnOption<TData>>()

inline fun <TData : RowData> AggregationFnOption(
    source: AggregationFn<TData>,
): AggregationFnOption<TData> =
    source.unsafeCast<AggregationFnOption<TData>>()
