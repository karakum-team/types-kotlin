// Automatically generated - do not modify!

package tanstack.query.core

import js.promise.Promise

// @JsPlainObject
// Details - https://youtrack.jetbrains.com/issue/KT-70664
external interface InfiniteQueryObserverBaseResult<TData, TError>
    : QueryObserverResult<TData, TError> {
    val fetchNextPage: (options: FetchNextPageOptions?) -> Promise<InfiniteQueryObserverResult<TData, TError>>
    val fetchPreviousPage: (options: FetchPreviousPageOptions?) -> Promise<InfiniteQueryObserverResult<TData, TError>>
    val hasNextPage: Boolean
    val hasPreviousPage: Boolean
    val isFetchNextPageError: Boolean
    val isFetchingNextPage: Boolean
    val isFetchPreviousPageError: Boolean
    val isFetchingPreviousPage: Boolean
}
