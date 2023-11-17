// Automatically generated - do not modify!

@file:JsModule("@tanstack/query-core")

@file:Suppress(
    "UNUSED_TYPEALIAS_PARAMETER",
    "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
)

package tanstack.query.core

import js.core.EpochTimeStamp
import js.core.Void
import js.iterable.IterableIterator
import js.promise.Promise
import js.promise.PromiseResult
import web.abort.AbortSignal

typealias MutationObserverListener<TData, TError, TVariables, TContext> = (result: MutationObserverResult<TData, TError, TVariables, TContext>) -> Unit

open external class MutationObserver<TData, TError, TVariables, TContext>(
    client: QueryClient,
    options: MutationObserverOptions<TData, TError, TVariables, TContext>,
) : Subscribable<MutationObserverListener<TData, TError, TVariables, TContext>> {
    open var options: MutationObserverOptions<TData, TError, TVariables, TContext>
    protected open fun bindMethods()
    open fun setOptions(options: MutationObserverOptions<TData, TError, TVariables, TContext> = definedExternally)
    override fun onUnsubscribe()
    open fun onMutationUpdate(action: Action_1<TData, TError, TVariables, TContext>)
    open fun getCurrentResult(): MutationObserverResult<TData, TError, TVariables, TContext>
    open fun reset()
    open fun mutate(
        variables: TVariables,
        options: MutateOptions<TData, TError, TVariables, TContext> = definedExternally,
    ): Promise<TData>
}

external interface QueryFilters {
    var type: QueryTypeFilter
    var exact: Boolean
    var predicate: (query: Query<*, *, *, *>) -> Boolean
    var queryKey: QueryKey
    var stale: Boolean
    var fetchStatus: FetchStatus
}

external interface MutationFilters {
    var exact: Boolean
    var predicate: (mutation: Mutation<*, *, *, *>) -> Boolean
    var mutationKey: MutationKey
    var status: MutationStatus
}

typealias Updater<TInput, TOutput> = Union /* TOutput | ((input: TInput) => TOutput) */

external val isServer: Boolean

external fun noop(): Void

external fun <TInput, TOutput> functionalUpdate(
    updater: Updater<TInput, TOutput>,
    input: TInput,
): TOutput

external fun isValidTimeout(value: Any?): Boolean /* value is number */

external fun timeUntilStale(
    updatedAt: EpochTimeStamp,
    staleTime: JsDuration = definedExternally,
): Int

external fun matchQuery(
    filters: QueryFilters,
    query: Query<*, *, *, *>,
): Boolean

external fun matchMutation(
    filters: MutationFilters,
    mutation: Mutation<*, *, *, *>,
): Boolean

external fun <TQueryKey : QueryKey> hashQueryKeyByOptions(
    queryKey: TQueryKey,
    options: QueryOptions<*, *, *, TQueryKey> = definedExternally,
): String

external fun hashKey(queryKey: Any /* QueryKey | MutationKey */): String

external fun partialMatchKey(
    a: QueryKey,
    b: QueryKey,
): Boolean

external fun <T> replaceEqualDeep(
    a: Any?,
    b: T,
): T

external fun <T> shallowEqualObjects(
    a: T,
    b: T,
): Boolean

external fun isPlainArray(value: Any?): Boolean

external fun isPlainObject(o: Any): Boolean /* o is Object */

external fun sleep(ms: Int): Promise<Unit>

external fun scheduleMicrotask(callback: () -> Unit)

external fun <TData, TOptions : QueryOptions<*, *, *, *>> replaceData(
    prevData: TData?,
    data: TData,
    options: TOptions,
): TData

external fun <T> keepPreviousData(previousData: T?): T?

external fun <T> addToEnd(
    items: Array<T>,
    item: T,
    max: Int = definedExternally,
): Array<T>

external fun <T> addToStart(
    items: Array<T>,
    item: T,
    max: Int = definedExternally,
): Array<T>

external interface MutationCacheConfig {
    var onError: (error: DefaultError, variables: Any?, context: Any?, mutation: Mutation<*, *, *, *>) -> Promise<*>?
    var onSuccess: (data: Any?, variables: Any?, context: Any?, mutation: Mutation<*, *, *, *>) -> Promise<*>?
    var onMutate: (variables: Any?, mutation: Mutation<*, *, *, *>) -> Promise<*>?
    var onSettled: (data: Any?, error: DefaultError?, variables: Any?, context: Any?, mutation: Mutation<*, *, *, *>) -> Promise<*>?
}

external interface NotifyEventMutationAdded : NotifyEvent {
    override var type: NotifyEventType /* 'added' */
    var mutation: Mutation<*, *, *, *>
}

external interface NotifyEventMutationRemoved : NotifyEvent {
    override var type: NotifyEventType /* 'removed' */
    var mutation: Mutation<*, *, *, *>
}

external interface NotifyEventMutationObserverAdded : NotifyEvent {
    override var type: NotifyEventType /* 'observerAdded' */
    var mutation: Mutation<*, *, *, *>
    var observer: MutationObserver<*, *, *, *>
}

external interface NotifyEventMutationObserverRemoved : NotifyEvent {
    override var type: NotifyEventType /* 'observerRemoved' */
    var mutation: Mutation<*, *, *, *>
    var observer: MutationObserver<*, *, *, *>
}

external interface NotifyEventMutationObserverOptionsUpdated : NotifyEvent {
    override var type: NotifyEventType /* 'observerOptionsUpdated' */
    var mutation: Mutation<*, *, *, *>
    var observer: MutationObserver<*, *, *, *>
}

external interface NotifyEventMutationUpdated : NotifyEvent {
    override var type: NotifyEventType /* 'updated' */
    var mutation: Mutation<*, *, *, *>
    var action: Action_1<*, *, *, *>
}

typealias MutationCacheNotifyEvent = Union /* NotifyEventMutationAdded | NotifyEventMutationRemoved | NotifyEventMutationObserverAdded | NotifyEventMutationObserverRemoved | NotifyEventMutationObserverOptionsUpdated | NotifyEventMutationUpdated */

typealias MutationCacheListener = (event: MutationCacheNotifyEvent) -> Unit

open external class MutationCache(config: MutationCacheConfig = definedExternally) :
    Subscribable<MutationCacheListener> {
    open var config: MutationCacheConfig
    open fun <TData, TError, TVariables, TContext> build(
        client: QueryClient,
        options: MutationOptions<TData, TError, TVariables, TContext>,
        state: MutationState<TData, TError, TVariables, TContext> = definedExternally,
    ): Mutation<TData, TError, TVariables, TContext>

    open fun add(mutation: Mutation<*, *, *, *>)
    open fun remove(mutation: Mutation<*, *, *, *>)
    open fun clear()
    open fun getAll(): Array<Mutation>
    open fun <TData, TError, TVariables, TContext> find(filters: MutationFilters): Mutation<TData, TError, TVariables, TContext>?
    open fun findAll(filters: MutationFilters = definedExternally): Array<Mutation>
    open fun notify(event: MutationCacheNotifyEvent)
    open fun resumePausedMutations(): Promise<Any?>
}

external interface MutationConfig<TData, TError, TVariables, TContext> {
    var mutationId: Int
    var mutationCache: MutationCache
    var options: MutationOptions<TData, TError, TVariables, TContext>
    var defaultOptions: MutationOptions<TData, TError, TVariables, TContext>
    var state: MutationState<TData, TError, TVariables, TContext>
}

external interface MutationState<TData, TError, TVariables, TContext> {
    val context: TContext?
    val data: TData?
    val error: TError?
    val failureCount: Int
    val failureReason: TError?
    val isPaused: Boolean
    val status: MutationStatus
    val variables: TVariables?
    val submittedAt: EpochTimeStamp
}

external interface FailedAction_1<TError> {
    var type: Type /* 'failed' */
    var failureCount: Int
    var error: TError?
}

external interface PendingAction<TVariables, TContext> {
    var type: Type /* 'pending' */
    var variables: TVariables
    var context: TContext
}

external interface SuccessAction_1<TData> {
    var type: Type /* 'success' */
    var data: TData
}

external interface ErrorAction_1<TError> {
    var type: Type /* 'error' */
    var error: TError
}

external interface PauseAction_1 {
    var type: Type /* 'pause' */
}

external interface ContinueAction_1 {
    var type: Type /* 'continue' */
}

typealias Action_1<TData, TError, TVariables, TContext> = Union /* ContinueAction_1 | ErrorAction_1<TError> | FailedAction_1<TError> | PendingAction<TVariables, TContext> | PauseAction_1 | SuccessAction_1<TData> */

open external class Mutation<TData, TError, TVariables, TContext>(config: MutationConfig<TData, TError, TVariables, TContext>) :
    Removable {
    open var state: MutationState<TData, TError, TVariables, TContext>
    open var options: MutationOptions<TData, TError, TVariables, TContext>
    open var readonly mutationId: Int
    open fun setOptions(options: MutationOptions<TData, TError, TVariables, TContext> = definedExternally)
    open var meta: MutationMeta?
    open fun addObserver(observer: MutationObserver<*, *, *, *>)
    open fun removeObserver(observer: MutationObserver<*, *, *, *>)
    override fun optionalRemove()
    open fun `continue`(): Promise<Any?>
    open fun execute(variables: TVariables): Promise<TData>
}

external fun <TData, TError, TVariables, TContext> getDefaultState(): MutationState<TData, TError, TVariables, TContext>

external interface RetryerConfig<TData, TError> {
    var fn: () -> PromiseResult<TData>
    var abort: () -> Unit
    var onError: (error: TError) -> Unit
    var onSuccess: (data: TData) -> Unit
    var onFail: (failureCount: Int, error: TError) -> Unit
    var onPause: () -> Unit
    var onContinue: () -> Unit
    var retry: RetryValue<TError>
    var retryDelay: RetryDelayValue<TError>
    var networkMode: NetworkMode?
}

external interface Retryer<TData> {
    var promise: Promise<TData>
    var cancel: (cancelOptions: CancelOptions?) -> Unit
    var `continue`: () -> Promise<Any?>
    var cancelRetry: () -> Unit
    var continueRetry: () -> Unit
}

typealias RetryValue<TError> = ShouldRetryFunction<TError>

typealias ShouldRetryFunction<TError> = (failureCount: Int, error: TError) -> Boolean

typealias RetryDelayValue<TError> = RetryDelayFunction<TError>

typealias RetryDelayFunction<TError> = (failureCount: Int, error: TError) -> Int

external fun canFetch(networkMode: NetworkMode?): Boolean

open external class CancelledError(options: CancelOptions = definedExternally) {
    open var revert: Boolean
    open var silent: Boolean
}

external fun isCancelledError(value: Any): Boolean /* value is CancelledError */

external fun <TData, TError> createRetryer(config: RetryerConfig<TData, TError>): Retryer<TData>

typealias NoInfer<T> = Any

external interface Register

typealias DefaultError = Any

// ReadonlyArray<unknown>
external interface QueryKey

external val dataTagSymbol: unique symbol

typealias DataTag<Type, Value> = Any

typealias QueryFunction<T, TQueryKey, TPageParam> = (context: QueryFunctionContext<TQueryKey, TPageParam>) -> Promise<T>

typealias QueryPersister<T, TQueryKey, TPageParam> = Any

typealias QueryFunctionContext<TQueryKey, TPageParam> = Any

typealias InitialDataFunction<T> = () -> T?

typealias NonFunctionGuard<T> = Any

typealias PlaceholderDataFunction<TQueryFnData, TError, TQueryData, TQueryKey> = (previousData: TQueryData?, previousQuery: Query<TQueryFnData, TError, TQueryData, TQueryKey>?) -> TQueryData?

typealias QueriesPlaceholderDataFunction<TQueryData> = (previousData: undefined, previousQuery: undefined) -> TQueryData?

typealias QueryKeyHashFunction<TQueryKey> = (queryKey: TQueryKey) -> String

typealias GetPreviousPageParamFunction<TPageParam, TQueryFnData> = Union /* (firstPage: TQueryFnData, allPages: Array<TQueryFnData>, firstPageParam: TPageParam, allPageParams: Array<TPageParam>) => TPageParam? | null */

typealias GetNextPageParamFunction<TPageParam, TQueryFnData> = Union /* (lastPage: TQueryFnData, allPages: Array<TQueryFnData>, lastPageParam: TPageParam, allPageParams: Array<TPageParam>) => TPageParam? | null */

external interface InfiniteData<TData, TPageParam> {
    var pages: Array<TData>
    var pageParams: Array<TPageParam>
}

typealias QueryMeta = Any

typealias NotifyOnChangeProps = Union /* Array<keyof InfiniteQueryObserverResult> | 'all' | (() => Array<keyof InfiniteQueryObserverResult> | 'all') */

external interface QueryOptions<TQueryFnData, TError, TData, TQueryKey : QueryKey, TPageParam> {
    var retry: RetryValue<TError>
    var retryDelay: RetryDelayValue<TError>
    var networkMode: NetworkMode
    var gcTime: JsDuration
    var queryFn: QueryFunction<TQueryFnData, TQueryKey, TPageParam>
    var persister: QueryPersister<NoInfer<TQueryFnData>, NoInfer<TQueryKey>, NoInfer<TPageParam>>
    var queryHash: String
    var queryKey: TQueryKey
    var queryKeyHashFn: QueryKeyHashFunction<TQueryKey>
    var initialData: InitialDataFunction<TData> /* | TData */
    var initialDataUpdatedAt: Any /* number | (() => number | undefined) */
    var behavior: QueryBehavior<TQueryFnData, TError, TData, TQueryKey>
    var structuralSharing: Any /* boolean | (<T>(oldData: T | undefined, newData: T) => T) */
    var _defaulted: Boolean
    var meta: QueryMeta
    var maxPages: Int
}

external interface InitialPageParam<TPageParam> {
    var initialPageParam: TPageParam
}

external interface InfiniteQueryPageParamsOptions<TQueryFnData, TPageParam>
    : InitialPageParam<TPageParam> {
    var getPreviousPageParam: GetPreviousPageParamFunction<TPageParam, TQueryFnData>
    var getNextPageParam: GetNextPageParamFunction<TPageParam, TQueryFnData>
}

typealias ThrowOnError<TQueryFnData, TError, TQueryData, TQueryKey> = Union /* boolean | ((error: TError, query: Query<TQueryFnData, TError, TQueryData, TQueryKey>) => boolean) */

external interface QueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey : QueryKey, TPageParam>
    : QueryOptions<TQueryFnData, TError, TQueryData, TQueryKey, TPageParam> {
    var enabled: Boolean
    var staleTime: JsDuration
    var refetchInterval: Any /* number | false | ((query: Query<TQueryFnData, TError, TQueryData, TQueryKey>) => number | false | undefined) */
    var refetchIntervalInBackground: Boolean
    var refetchOnWindowFocus: (query: Query<TQueryFnData, TError, TQueryData, TQueryKey>) -> Boolean /* | boolean | 'always' */
    var refetchOnReconnect: (query: Query<TQueryFnData, TError, TQueryData, TQueryKey>) -> Boolean /* | boolean | 'always' */
    var refetchOnMount: (query: Query<TQueryFnData, TError, TQueryData, TQueryKey>) -> Boolean /* | boolean | 'always' */
    var retryOnMount: Boolean
    var notifyOnChangeProps: NotifyOnChangeProps
    var throwOnError: ThrowOnError<TQueryFnData, TError, TQueryData, TQueryKey>
    var select: (data: TQueryData) -> TData
    var suspense: Boolean
    var placeholderData: Any /* NonFunctionGuard<TQueryData> | PlaceholderDataFunction<NonFunctionGuard<TQueryData>, TError, NonFunctionGuard<TQueryData>, TQueryKey> */
    var _optimisticResults: String /* 'optimistic' | 'isRestoring' */
}

typealias WithRequired<T, K> = Any

typealias DefaultedQueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey> = Union /* WithRequired<QueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey>, 'throwOnError' | 'refetchOnReconnect'> */

external interface InfiniteQueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey : QueryKey, TPageParam>
    : QueryObserverOptions<TQueryFnData, TError, TData, InfiniteData<TQueryData, TPageParam>, TQueryKey, TPageParam>,
    InfiniteQueryPageParamsOptions<TQueryFnData, TPageParam>

typealias DefaultedInfiniteQueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey, TPageParam> = Union /* WithRequired<InfiniteQueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey, TPageParam>, 'throwOnError' | 'refetchOnReconnect'> */

external interface FetchQueryOptions<TQueryFnData, TError, TData, TQueryKey : QueryKey, TPageParam>
    : WithRequired<QueryOptions<TQueryFnData, TError, TData, TQueryKey, TPageParam>, 'queryKey'> {
    var staleTime: JsDuration
}

typealias FetchInfiniteQueryPages<TQueryFnData, TPageParam> = Any

typealias FetchInfiniteQueryOptions<TQueryFnData, TError, TData, TQueryKey, TPageParam> = Any

external interface ResultOptions {
    var throwOnError: Boolean
}

external interface RefetchOptions : ResultOptions {
    var cancelRefetch: Boolean
}

external interface InvalidateQueryFilters : QueryFilters {
    var refetchType: QueryTypeFilter /* | 'none' */
}

external interface RefetchQueryFilters : QueryFilters

external interface InvalidateOptions : RefetchOptions

external interface ResetOptions : RefetchOptions

external interface FetchNextPageOptions : ResultOptions {
    var cancelRefetch: Boolean
}

external interface FetchPreviousPageOptions : ResultOptions {
    var cancelRefetch: Boolean
}

external interface QueryObserverBaseResult<TData, TError> {
    val data: TData?
    val dataUpdatedAt: EpochTimeStamp
    val error: TError?
    val errorUpdatedAt: EpochTimeStamp
    val failureCount: Int
    val failureReason: TError?
    val errorUpdateCount: Int
    val isError: Boolean
    val isFetched: Boolean
    val isFetchedAfterMount: Boolean
    val isFetching: Boolean
    val isLoading: Boolean
    val isPending: Boolean
    val isLoadingError: Boolean
    val isInitialLoading: Boolean
    val isPaused: Boolean
    val isPlaceholderData: Boolean
    val isRefetchError: Boolean
    val isRefetching: Boolean
    val isStale: Boolean
    val isSuccess: Boolean
    val refetch: (options: RefetchOptions?) -> Promise<QueryObserverResult<TData, TError>>
    val status: QueryStatus
    val fetchStatus: FetchStatus
}

external interface QueryObserverLoadingResult<TData, TError>
    : QueryObserverResult<TData, TError> {
    override val data: Void
    override val error: Void
    override val isError: False
    override val isPending: True
    override val isLoadingError: False
    override val isRefetchError: False
    override val isSuccess: False
    override val status: QueryStatus /* 'pending' */
}

external interface QueryObserverLoadingErrorResult<TData, TError>
    : QueryObserverResult<TData, TError> {
    override val data: Void
    override val error: TError
    override val isError: True
    override val isPending: False
    override val isLoadingError: True
    override val isRefetchError: False
    override val isSuccess: False
    override val status: QueryStatus /* 'error' */
}

external interface QueryObserverRefetchErrorResult<TData, TError>
    : QueryObserverResult<TData, TError> {
    override val data: TData
    override val error: TError
    override val isError: True
    override val isPending: False
    override val isLoadingError: False
    override val isRefetchError: True
    override val isSuccess: False
    override val status: QueryStatus /* 'error' */
}

external interface QueryObserverSuccessResult<TData, TError>
    : QueryObserverResult<TData, TError> {
    override val data: TData
    override val error: Void
    override val isError: False
    override val isPending: False
    override val isLoadingError: False
    override val isRefetchError: False
    override val isSuccess: True
    override val status: QueryStatus /* 'success' */
}

sealed external interface DefinedQueryObserverResult<TData, TError>
    : QueryObserverBaseResult<TData, TError>

sealed external interface QueryObserverResult<TData, TError>
    : QueryObserverBaseResult<TData, TError>

external interface InfiniteQueryObserverBaseResult<TData, TError>
    : QueryObserverResult<TData, TError> {
    val fetchNextPage: (options: FetchNextPageOptions?) -> Promise<InfiniteQueryObserverResult<TData, TError>>
    val fetchPreviousPage: (options: FetchPreviousPageOptions?) -> Promise<InfiniteQueryObserverResult<TData, TError>>
    val hasNextPage: Boolean
    val hasPreviousPage: Boolean
    val isFetchingNextPage: Boolean
    val isFetchingPreviousPage: Boolean
}

external interface InfiniteQueryObserverLoadingResult<TData, TError>
    : InfiniteQueryObserverResult<TData, TError> {
    override val data: Void
    override val error: Void
    override val isError: False
    override val isPending: True
    override val isLoadingError: False
    override val isRefetchError: False
    override val isSuccess: False
    override val status: QueryStatus /* 'pending' */
}

external interface InfiniteQueryObserverLoadingErrorResult<TData, TError>
    : InfiniteQueryObserverResult<TData, TError> {
    override val data: Void
    override val error: TError
    override val isError: True
    override val isPending: False
    override val isLoadingError: True
    override val isRefetchError: False
    override val isSuccess: False
    override val status: QueryStatus /* 'error' */
}

external interface InfiniteQueryObserverRefetchErrorResult<TData, TError>
    : InfiniteQueryObserverResult<TData, TError> {
    override val data: TData
    override val error: TError
    override val isError: True
    override val isPending: False
    override val isLoadingError: False
    override val isRefetchError: True
    override val isSuccess: False
    override val status: QueryStatus /* 'error' */
}

external interface InfiniteQueryObserverSuccessResult<TData, TError>
    : InfiniteQueryObserverResult<TData, TError> {
    override val data: TData
    override val error: Void
    override val isError: False
    override val isPending: False
    override val isLoadingError: False
    override val isRefetchError: False
    override val isSuccess: True
    override val status: QueryStatus /* 'success' */
}

sealed external interface DefinedInfiniteQueryObserverResult<TData, TError>
    : InfiniteQueryObserverBaseResult<TData, TError>

sealed external interface InfiniteQueryObserverResult<TData, TError>
    : InfiniteQueryObserverBaseResult<TData, TError>

typealias MutationKey = Any

typealias MutationMeta = Any

typealias MutationFunction<TData, TVariables> = (variables: TVariables) -> Promise<TData>

external interface MutationOptions<TData, TError, TVariables, TContext> {
    var mutationFn: MutationFunction<TData, TVariables>
    var mutationKey: MutationKey
    var onMutate: (variables: TVariables) -> Promise<TContext?>?
    var onSuccess: (data: TData, variables: TVariables, context: TContext?) -> Promise<*>?
    var onError: (error: TError, variables: TVariables, context: TContext?) -> Promise<*>?
    var onSettled: (data: TData?, error: TError?, variables: TVariables, context: TContext?) -> Promise<*>?
    var retry: RetryValue<TError>
    var retryDelay: RetryDelayValue<TError>
    var networkMode: NetworkMode
    var gcTime: JsDuration
    var _defaulted: Boolean
    var meta: MutationMeta
}

external interface MutationObserverOptions<TData, TError, TVariables, TContext>
    : MutationOptions<TData, TError, TVariables, TContext> {
    var throwOnError: (error: TError) -> Boolean
}

external interface MutateOptions<TData, TError, TVariables, TContext> {
    var onSuccess: (data: TData, variables: TVariables, context: TContext) -> Unit
    var onError: (error: TError, variables: TVariables, context: TContext?) -> Unit
    var onSettled: (data: TData?, error: TError?, variables: TVariables, context: TContext?) -> Unit
}

typealias MutateFunction<TData, TError, TVariables, TContext> = (variables: TVariables, options: MutateOptions<TData, TError, TVariables, TContext>?) -> Promise<TData>

external interface MutationObserverBaseResult<TData, TError, TVariables, TContext>
    : MutationState<TData, TError, TVariables, TContext> {
    val isError: Boolean
    val isIdle: Boolean
    val isPending: Boolean
    val isSuccess: Boolean
    val mutate: MutateFunction<TData, TError, TVariables, TContext>
    val reset: () -> Unit
}

external interface MutationObserverIdleResult<TData, TError, TVariables, TContext>
    : MutationObserverResult<TData, TError, TVariables, TContext> {
    override val data: Void
    override val variables: Void
    override val error: Void
    override val isError: False
    override val isIdle: True
    override val isPending: False
    override val isSuccess: False
    override val status: MutationStatus /* 'idle' */
}

external interface MutationObserverLoadingResult<TData, TError, TVariables, TContext>
    : MutationObserverResult<TData, TError, TVariables, TContext> {
    override val data: Void
    override val variables: TVariables
    override val error: Void
    override val isError: False
    override val isIdle: False
    override val isPending: True
    override val isSuccess: False
    override val status: MutationStatus /* 'pending' */
}

external interface MutationObserverErrorResult<TData, TError, TVariables, TContext>
    : MutationObserverResult<TData, TError, TVariables, TContext> {
    override val data: Void
    override val error: TError
    override val variables: TVariables
    override val isError: True
    override val isIdle: False
    override val isPending: False
    override val isSuccess: False
    override val status: MutationStatus /* 'error' */
}

external interface MutationObserverSuccessResult<TData, TError, TVariables, TContext>
    : MutationObserverResult<TData, TError, TVariables, TContext> {
    override val data: TData
    override val error: Void
    override val variables: TVariables
    override val isError: False
    override val isIdle: False
    override val isPending: False
    override val isSuccess: True
    override val status: MutationStatus /* 'success' */
}

external interface MutationObserverResult<TData, TError, TVariables, TContext>
    : MutationObserverBaseResult<TData, TError, TVariables, TContext>

external interface QueryClientConfig {
    var queryCache: QueryCache
    var mutationCache: MutationCache
    var defaultOptions: DefaultOptions<*>
}

external interface DefaultOptions<TError> {
    var queries: QueryObserverOptions<*, TError, *, *, *>
    var mutations: MutationObserverOptions<*, TError, *, *>
}

external interface CancelOptions {
    var revert: Boolean
    var silent: Boolean
}

external interface SetDataOptions {
    var updatedAt: EpochTimeStamp
}

external interface NotifyEvent {
    var type: NotifyEventType
}

typealias QueryObserverListener<TData, TError> = (result: QueryObserverResult<TData, TError>) -> Unit

external interface NotifyOptions {
    var listeners: Boolean
}

external interface ObserverFetchOptions : FetchOptions {
    var throwOnError: Boolean
}

open external class QueryObserver<TQueryFnData, TError, TData, TQueryData, TQueryKey : QueryKey>(
    client: QueryClient,
    options: QueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey>,
) : Subscribable<QueryObserverListener<TData, TError>> {
    open var options: QueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey>
    protected open fun bindMethods()
    override fun onSubscribe()
    override fun onUnsubscribe()
    open fun shouldFetchOnReconnect(): Boolean
    open fun shouldFetchOnWindowFocus(): Boolean
    open fun destroy()
    open fun setOptions(
        options: QueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey> = definedExternally,
        notifyOptions: NotifyOptions = definedExternally,
    )

    open fun getOptimisticResult(options: DefaultedQueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey>): QueryObserverResult<TData, TError>
    open fun getCurrentResult(): QueryObserverResult<TData, TError>
    open fun trackResult(result: QueryObserverResult<TData, TError>): QueryObserverResult<TData, TError>
    open fun getCurrentQuery(): Query<TQueryFnData, TError, TQueryData, TQueryKey>
    open fun refetch( {
        ...options
    }: RefetchOptions  = definedExternally): Promise<QueryObserverResult<TData, TError>>
    open fun fetchOptimistic(options: QueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey>): Promise<QueryObserverResult<TData, TError>>
    protected open fun fetch(fetchOptions: ObserverFetchOptions): Promise<QueryObserverResult<TData, TError>>
    protected open fun createResult(
        query: Query<TQueryFnData, TError, TQueryData, TQueryKey>,
        options: QueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey>,
    ): QueryObserverResult<TData, TError>

    open fun updateResult(notifyOptions: NotifyOptions = definedExternally)
    open fun onQueryUpdate()
}

external interface QueryConfig<TQueryFnData, TError, TData, TQueryKey : QueryKey> {
    var cache: QueryCache
    var queryKey: TQueryKey
    var queryHash: String
    var options: QueryOptions<TQueryFnData, TError, TData, TQueryKey>
    var defaultOptions: QueryOptions<TQueryFnData, TError, TData, TQueryKey>
    var state: QueryState<TData, TError>
}

external interface QueryState<TData, TError> {
    var data: TData?
    var dataUpdateCount: Int
    var dataUpdatedAt: EpochTimeStamp
    var error: TError?
    var errorUpdateCount: Int
    var errorUpdatedAt: EpochTimeStamp
    var fetchFailureCount: Int
    var fetchFailureReason: TError?
    var fetchMeta: FetchMeta | null
    var isInvalidated: Boolean
    var status: QueryStatus
    var fetchStatus: FetchStatus
}

external interface FetchContext<TQueryFnData, TError, TData, TQueryKey : QueryKey> {
    var fetchFn: () -> Promise<*>?
    var fetchOptions: FetchOptions
    var signal: AbortSignal
    var options: QueryOptions<TQueryFnData, TError, TData, *>
    var queryKey: TQueryKey
    var state: QueryState<TData, TError>
}

external interface QueryBehavior<TQueryFnData, TError, TData, TQueryKey : QueryKey> {
    var onFetch: (context: FetchContext<TQueryFnData, TError, TData, TQueryKey>, query: Query<*, *, *, *>) -> Unit
}

external interface FetchMeta {
    var fetchMore: dynamic /* { */
    fun
}(}: } ): }
}

external interface FetchOptions {
    var cancelRefetch: Boolean
    var meta: FetchMeta
}

external interface FailedAction<TError> {
    var type: Type /* 'failed' */
    var failureCount: Int
    var error: TError
}

external interface FetchAction {
    var type: Type /* 'fetch' */
    var meta: FetchMeta
}

external interface SuccessAction<TData> {
    var data: TData?
    var type: Type /* 'success' */
    var dataUpdatedAt: EpochTimeStamp
    var manual: Boolean
}

external interface ErrorAction<TError> {
    var type: Type /* 'error' */
    var error: TError
}

external interface InvalidateAction {
    var type: Type /* 'invalidate' */
}

external interface PauseAction {
    var type: Type /* 'pause' */
}

external interface ContinueAction {
    var type: Type /* 'continue' */
}

external interface SetStateAction<TData, TError> {
    var type: Type /* 'setState' */
    var state: QueryState<TData, TError> /* Partial */
    var setStateOptions: SetStateOptions
}

typealias Action<TData, TError> = Union /* ContinueAction | ErrorAction<TError> | FailedAction<TError> | FetchAction | InvalidateAction | PauseAction | SetStateAction<TData, TError> | SuccessAction<TData> */

external interface SetStateOptions {
    var meta: Any
}

open external class Query<TQueryFnData, TError, TData, TQueryKey : QueryKey>(config: QueryConfig<TQueryFnData, TError, TData, TQueryKey>) :
    Removable {
    open var queryKey: TQueryKey
    open var queryHash: String
    open var options: QueryOptions<TQueryFnData, TError, TData, TQueryKey>
    open var state: QueryState<TData, TError>
    open var isFetchingOptimistic: Boolean
    open var meta: QueryMeta?
    override fun optionalRemove()
    open fun setData(
        newData: TData,
        options: SetDataOptions = definedExternally,
    ): TData

    open fun setState(
        state: QueryState<TData, TError>, /* Partial */
        setStateOptions: SetStateOptions = definedExternally,
    )

    open fun cancel(options: CancelOptions = definedExternally): Promise<Unit>
    override fun destroy()
    open fun reset()
    open fun isActive(): Boolean
    open fun isDisabled(): Boolean
    open fun isStale(): Boolean
    open fun isStaleByTime(staleTime: JsDuration = definedExternally): Boolean
    open fun onFocus()
    open fun onOnline()
    open fun addObserver(observer: QueryObserver<*, *, *, *, *>)
    open fun removeObserver(observer: QueryObserver<*, *, *, *, *>)
    open fun getObserversCount(): Int
    open fun invalidate()
    open fun fetch(
        options: QueryOptions<TQueryFnData, TError, TData, TQueryKey> = definedExternally,
        fetchOptions: FetchOptions = definedExternally,
    ): Promise<TData>
}

external interface QueryCacheConfig {
    var onError: (error: DefaultError, query: Query<*, *, *, *>) -> Unit
    var onSuccess: (data: Any, query: Query<*, *, *, *>) -> Unit
    var onSettled: (data: Any?, error: DefaultError?, query: Query<*, *, *, *>) -> Unit
}

external interface NotifyEventQueryAdded : NotifyEvent {
    override var type: NotifyEventType /* 'added' */
    var query: Query<*, *, *, *>
}

external interface NotifyEventQueryRemoved : NotifyEvent {
    override var type: NotifyEventType /* 'removed' */
    var query: Query<*, *, *, *>
}

external interface NotifyEventQueryUpdated : NotifyEvent {
    override var type: NotifyEventType /* 'updated' */
    var query: Query<*, *, *, *>
    var action: Action<*, *>
}

external interface NotifyEventQueryObserverAdded : NotifyEvent {
    override var type: NotifyEventType /* 'observerAdded' */
    var query: Query<*, *, *, *>
    var observer: QueryObserver<*, *, *, *, *>
}

external interface NotifyEventQueryObserverRemoved : NotifyEvent {
    override var type: NotifyEventType /* 'observerRemoved' */
    var query: Query<*, *, *, *>
    var observer: QueryObserver<*, *, *, *, *>
}

external interface NotifyEventQueryObserverResultsUpdated : NotifyEvent {
    override var type: NotifyEventType /* 'observerResultsUpdated' */
    var query: Query<*, *, *, *>
}

external interface NotifyEventQueryObserverOptionsUpdated : NotifyEvent {
    override var type: NotifyEventType /* 'observerOptionsUpdated' */
    var query: Query<*, *, *, *>
    var observer: QueryObserver<*, *, *, *, *>
}

typealias QueryCacheNotifyEvent = Union /* NotifyEventQueryAdded | NotifyEventQueryRemoved | NotifyEventQueryUpdated | NotifyEventQueryObserverAdded | NotifyEventQueryObserverRemoved | NotifyEventQueryObserverResultsUpdated | NotifyEventQueryObserverOptionsUpdated */

typealias QueryCacheListener = (event: QueryCacheNotifyEvent) -> Unit

external interface QueryStore {
    var has: (queryKey: String) -> Boolean
    var set: (queryKey: String, query: Query<*, *, *, *>) -> Unit
    var get: (queryKey: String) -> Query?
    var delete: (queryKey: String) -> Unit
    var values: () -> IterableIterator<Query>
}

open external class QueryCache(config: QueryCacheConfig = definedExternally) : Subscribable<QueryCacheListener> {
    open var config: QueryCacheConfig
    open fun <TQueryFnData, TError, TData, TQueryKey : QueryKey> build(
        client: QueryClient,
        options: QueryOptions<TQueryFnData, TError, TData, TQueryKey>,
        state: QueryState<TData, TError> = definedExternally,
    ): Query<TQueryFnData, TError, TData, TQueryKey>

    open fun add(query: Query<*, *, *, *>)
    open fun remove(query: Query<*, *, *, *>)
    open fun clear()
    open fun <TQueryFnData, TError, TData, TQueryKey : QueryKey> get(queryHash: String): Query<TQueryFnData, TError, TData, TQueryKey>?
    open fun getAll(): Array<Query>
    open fun <TQueryFnData, TError, TData> find(filters: WithRequired<QueryFilters, 'queryKey'>): Query<TQueryFnData, TError, TData, *>?
    open fun findAll(filters: QueryFilters = definedExternally): Array<Query>
    open fun notify(event: QueryCacheNotifyEvent)
    open fun onFocus()
    open fun onOnline()
}

open external class QueryClient(config: QueryClientConfig = definedExternally) {
    open fun mount()
    open fun unmount()
    open fun isFetching(filters: QueryFilters = definedExternally): Int
    open fun isMutating(filters: MutationFilters = definedExternally): Int
    open var getQueryData: TQueryFnData>(queryKey: TaggedQueryKey): TInferredQueryFnData?
    open fun <TQueryFnData, TError, TData, TQueryKey : QueryKey> ensureQueryData(options: FetchQueryOptions<TQueryFnData, TError, TData, TQueryKey>): Promise<TData>
    open fun <TQueryFnData> getQueriesData(filters: QueryFilters): Any /* Array<[QueryKey, TQueryFnData | undefined]> */
    open var setQueryData: Any /* TQueryFnData>(queryKey: TaggedQueryKey, updater: Updater<NoInfer<TInferredQueryFnData> | undefined, NoInfer<TInferredQueryFnData> | undefined>, options?: SetDataOptions): TInferredQueryFnData | undefined */
    open fun <TQueryFnData> setQueriesData(
        filters: QueryFilters,
        updater: Updater<TQueryFnData?, TQueryFnData?>,
        options: SetDataOptions = definedExternally,
    ): Any /* Array<[QueryKey, TQueryFnData | undefined]> */

    open fun <TQueryFnData, TError> getQueryState(queryKey: QueryKey): QueryState<TQueryFnData, TError>?
    open fun removeQueries(filters: QueryFilters = definedExternally)
    open fun resetQueries(
        filters: QueryFilters = definedExternally,
        options: ResetOptions = definedExternally,
    ): Promise<Unit>

    open fun cancelQueries(
        filters: QueryFilters = definedExternally,
        cancelOptions: CancelOptions = definedExternally,
    ): Promise<Unit>

    open fun invalidateQueries(
        filters: InvalidateQueryFilters<*> = definedExternally,
        options: InvalidateOptions = definedExternally,
    ): Promise<Unit>

    open fun refetchQueries(
        filters: RefetchQueryFilters<*> = definedExternally,
        options: RefetchOptions = definedExternally,
    ): Promise<Unit>

    open fun <TQueryFnData, TError, TData, TQueryKey : QueryKey, TPageParam> fetchQuery(options: FetchQueryOptions<TQueryFnData, TError, TData, TQueryKey, TPageParam>): Promise<TData>
    open fun <TQueryFnData, TError, TData, TQueryKey : QueryKey> prefetchQuery(options: FetchQueryOptions<TQueryFnData, TError, TData, TQueryKey>): Promise<Unit>
    open fun <TQueryFnData, TError, TData, TQueryKey : QueryKey, TPageParam> fetchInfiniteQuery(options: FetchInfiniteQueryOptions<TQueryFnData, TError, TData, TQueryKey, TPageParam>): Promise<InfiniteData<TData, TPageParam>>
    open fun <TQueryFnData, TError, TData, TQueryKey : QueryKey, TPageParam> prefetchInfiniteQuery(options: FetchInfiniteQueryOptions<TQueryFnData, TError, TData, TQueryKey, TPageParam>): Promise<Unit>
    open fun resumePausedMutations(): Promise<Any?>
    open fun getQueryCache(): QueryCache
    open fun getMutationCache(): MutationCache
    open fun getDefaultOptions(): DefaultOptions<*>
    open fun setDefaultOptions(options: DefaultOptions<*>)
    open fun setQueryDefaults(
        queryKey: QueryKey,
        options: QueryObserverOptions<*, *, *, *, *>, /* Partial */
    )

    open fun getQueryDefaults(queryKey: QueryKey): QueryObserverOptions<*, *, *, *, *>
    open fun setMutationDefaults(
        mutationKey: MutationKey,
        options: MutationObserverOptions<*, *, *, *>,
    )

    open fun getMutationDefaults(mutationKey: MutationKey): MutationObserverOptions<*, *, *, *>
    open fun <TQueryFnData, TError, TData, TQueryData, TQueryKey : QueryKey, TPageParam> defaultQueryOptions(options: QueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey, TPageParam> | DefaultedQueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey>  = definedExternally): DefaultedQueryObserverOptions<TQueryFnData, TError, TData, TQueryData, TQueryKey>
    open fun <T : MutationOptions<*, *, *, *>> defaultMutationOptions(options: T = definedExternally): T
    open fun clear()
}
