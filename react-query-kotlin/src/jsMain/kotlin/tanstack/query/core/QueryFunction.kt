// Automatically generated - do not modify!

@file:Suppress(
    "NOTHING_TO_INLINE",
)

package tanstack.query.core

import js.promise.PromiseResult

sealed external interface QueryFunction<T, TQueryKey : QueryKey, TPageParam>

inline fun <T, TQueryKey : QueryKey, TPageParam> QueryFunction(
    noinline value: (context: QueryFunctionContext<TQueryKey, TPageParam>) -> PromiseResult<T>,
): QueryFunction<T, TQueryKey, TPageParam> =
    value.unsafeCast<QueryFunction<T, TQueryKey, TPageParam>>()
