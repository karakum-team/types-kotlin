// Automatically generated - do not modify!

@file:JsModule("@tanstack/query-core")

@file:Suppress(
    "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
)

package tanstack.query.core

import js.core.ReadonlyArray

typealias QueriesObserverListener = (result: ReadonlyArray<QueryObserverResult<*, *>>) -> Unit

external interface QueriesObserverOptions<TCombinedResult> {
    var combine: (result: ReadonlyArray<QueryObserverResult<*, *>>) -> TCombinedResult
}

open external class QueriesObserver<TCombinedResult>(
    client: QueryClient,
    queries: ReadonlyArray<QueryObserverOptions<*, *, *, *, *, *>>,
    options: QueriesObserverOptions<TCombinedResult> = definedExternally,
) : Subscribable<QueriesObserverListener> {
    override fun onSubscribe()
    override fun onUnsubscribe()
    open fun destroy()
    open fun setQueries(
        queries: ReadonlyArray<QueryObserverOptions<*, *, *, *, *, *>>,
        options: QueriesObserverOptions<TCombinedResult> = definedExternally,
        notifyOptions: NotifyOptions = definedExternally,
    )

    open fun getCurrentResult(): TCombinedResult
    open fun getQueries(): ReadonlyArray<Query<*, Error, *, QueryKey>>
    open fun getObservers(): ReadonlyArray<QueryObserver<*, Error, *, *, QueryKey>>
    open fun getOptimisticResult(queries: ReadonlyArray<QueryObserverOptions<*, *, *, *, *, *>>): [
    open fun ](]: ] ): ]
}
