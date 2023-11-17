// Automatically generated - do not modify!

@file:JsModule("@tanstack/react-query")

package tanstack.react.query

import tanstack.query.core.QueryClient
import tanstack.query.core.QueryKey

external fun <TQueryFnData, TError, TData, TQueryKey : QueryKey, TPageParam> useInfiniteQuery(
    options: UndefinedInitialDataInfiniteOptions<TQueryFnData, TError, TData, TQueryKey, TPageParam>,
    queryClient: QueryClient = definedExternally,
): UseInfiniteQueryResult<TData, TError>

external fun <TQueryFnData, TError, TData, TQueryKey : QueryKey, TPageParam> useInfiniteQuery(
    options: DefinedInitialDataInfiniteOptions<TQueryFnData, TError, TData, TQueryKey, TPageParam>,
    queryClient: QueryClient = definedExternally,
): DefinedUseInfiniteQueryResult<TData, TError>

external fun <TQueryFnData, TError, TData, TQueryKey : QueryKey, TPageParam> useInfiniteQuery(
    options: UseInfiniteQueryOptions<TQueryFnData, TError, TData, TQueryFnData, TQueryKey, TPageParam>,
    queryClient: QueryClient = definedExternally,
): UseInfiniteQueryResult<TData, TError>
