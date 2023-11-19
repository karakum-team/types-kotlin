// Automatically generated - do not modify!

@file:Suppress(
    "UNUSED_TYPEALIAS_PARAMETER",
)

package tanstack.react.query

import tanstack.query.core.*
import tanstack.query.core.DefinedQueryObserverResult
import tanstack.query.core.QueryObserverResult

external interface UseBaseQueryOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey : QueryKey>
    : QueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey>

external interface UseQueryOptions<TQueryFnData, TError, TData, TQueryKey : QueryKey>
    : UseBaseQueryOptions<TQueryFnData, TError, TData, TQueryFnData, TQueryKey>

external interface UseSuspenseQueryOptions<TQueryFnData, TError, TData, TQueryKey : QueryKey>
    : UseQueryOptions<TQueryFnData, TError, TData, TQueryKey>

external interface UseInfiniteQueryOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey : QueryKey, TPageParam>
    : InfiniteQueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey, TPageParam>

external interface UseSuspenseInfiniteQueryOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey : QueryKey, TPageParam>
    : UseInfiniteQueryOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey, TPageParam>

typealias UseBaseQueryResult<TData, TError> = QueryObserverResult<TData, TError>

typealias UseQueryResult<TData, TError> = UseBaseQueryResult<TData, TError>

typealias UseSuspenseQueryResult<TData, TError> = Omit<DefinedQueryObserverResult<TData, TError>, 'isPlaceholderData'>

typealias DefinedUseQueryResult<TData, TError> = DefinedQueryObserverResult<TData, TError>

typealias UseInfiniteQueryResult<TData, TError> = InfiniteQueryObserverResult<TData, TError>

typealias DefinedUseInfiniteQueryResult<TData, TError> = DefinedInfiniteQueryObserverResult<TData, TError>

typealias UseSuspenseInfiniteQueryResult<TData, TError> = Omit<DefinedInfiniteQueryObserverResult<TData, TError>, 'isPlaceholderData'>

external interface UseMutationOptions<TData, TError, TVariables, TContext>
    : MutationObserverOptions<TData, TError, TVariables, TContext>

typealias UseMutateFunction<TData, TError, TVariables, TContext> = Function<Unit> /* (...args: Parameters<MutateFunction<TData, TError, TVariables, TContext>>) => void */

typealias UseMutateAsyncFunction<TData, TError, TVariables, TContext> = MutateFunction<TData, TError, TVariables, TContext>

external interface UseBaseMutationResult<TData, TError, TVariables, TContext> :
    MutationObserverResult<TData, TError, TVariables, TContext> {
    // override val mutate: UseMutateFunction<TData, TError, TVariables, TContext>
    val mutateAsync: UseMutateAsyncFunction<TData, TError, TVariables, TContext>
}

typealias UseMutationResult<TData, TError, TVariables, TContext> = UseBaseMutationResult<TData, TError, TVariables, TContext>
