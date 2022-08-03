// Automatically generated - do not modify!

@file:Suppress(
    "NOTHING_TO_INLINE",
)

package tanstack.table.core

sealed external interface StringOrTemplateHeader<TData, TValue> /* string | ColumnDefTemplate<HeaderContext<TData, TValue>> */

inline fun <TData, TValue> StringOrTemplateHeader(
    source: String,
): StringOrTemplateHeader<TData, TValue> =
    source.unsafeCast<StringOrTemplateHeader<TData, TValue>>()

inline fun <TData, TValue> StringOrTemplateHeader(
    source: ColumnDefTemplate<HeaderContext<TData, TValue>>,
): StringOrTemplateHeader<TData, TValue> =
    source.unsafeCast<StringOrTemplateHeader<TData, TValue>>()
