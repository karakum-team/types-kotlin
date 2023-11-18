// Automatically generated - do not modify!

package tanstack.query.core

external interface FetchQueryOptions<TQueryFnData, TError, TData, TQueryKey : QueryKey, TPageParam>
    : WithRequired<QueryOptions<TQueryFnData, TError, TData, TQueryKey, TPageParam>, 'queryKey'> {
    var staleTime: JsDuration
}
