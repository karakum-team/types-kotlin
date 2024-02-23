// Automatically generated - do not modify!

@file:JsModule("@tanstack/react-query")

package tanstack.react.query

import tanstack.query.core.InfiniteQueryObserverOptions
import tanstack.query.core.InfiniteQueryObserverResult
import tanstack.query.core.DefinedInfiniteQueryObserverResult
import tanstack.query.core.DefinedQueryObserverResult
import tanstack.query.core.HydrateOptions
import tanstack.query.core.QueryClient
import tanstack.query.core.QueryFunction
import tanstack.query.core.QueryKey
import tanstack.query.core.QueryObserver
import tanstack.query.core.QueryObserverOptions
import tanstack.query.core.QueryObserverResult
import tanstack.query.core.QueryStatus
import tanstack.query.core.MutationObserverOptions
import tanstack.query.core.MutationFilters
import tanstack.query.core.MutationFunction
import tanstack.query.core.MutationKey
import tanstack.query.core.MutateFunction
import tanstack.query.core.MutationObserverResult
import tanstack.query.core.QueryFilters

import tanstack.query.core.False
import tanstack.query.core.True

external fun <TQueryFnData, TError, TData, TQueryKey: QueryKey, TPageParam> useInfiniteQuery(options: UseInfiniteQueryOptions<TQueryFnData, TError, TData, TQueryFnData, TQueryKey, TPageParam> ,
queryClient: QueryClient  = definedExternally): UseInfiniteQueryResult<TData, TError>