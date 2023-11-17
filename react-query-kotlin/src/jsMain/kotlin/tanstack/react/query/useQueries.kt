// Automatically generated - do not modify!

@file:JsModule("@tanstack/react-query")

@file:Suppress(
    "UNUSED_TYPEALIAS_PARAMETER",
    "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
)

package tanstack.react.query

typealias UseQueryOptionsForUseQueries<TQueryFnData, TError, TData, TQueryKey> = Union /* Omit<UseQueryOptions<TQueryFnData, TError, TData, TQueryKey>, 'placeholderData' | 'suspense'> & { */

const val MAXIMUM_DEPTH = 20

typealias GetOptions<T> = Any

typealias GetResults<T> = Any

typealias QueriesOptions<T, Result> = Any

typealias QueriesResults<T, Result> = Any

external fun <T : Array<any>, TCombinedResult> useQueries( {
    queries, ...options
}: dynamic /* { */ ): QueriesResults<T, *, *>
