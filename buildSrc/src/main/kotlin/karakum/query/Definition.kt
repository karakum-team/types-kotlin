package karakum.query

import java.io.File

private val DECLARE_KEYWORDS = setOf(
    "export",
    "declare",
)

private val TYPE_KEYWORDS = setOf(
    JsTypeKeyword.CONST,
    JsTypeKeyword.FUNCTION,
    JsTypeKeyword.TYPE,
    JsTypeKeyword.INTERFACE,
    JsTypeKeyword.CLASS,
)

fun toDeclarations(
    definitionFile: File,
): List<Declaration> {
    val fixAction = definitionFile.name == "mutation.d.ts"

    var content = definitionFile.readText()
        .splitToSequence("\n")
        .filter { !it.startsWith("export ") }
        .joinToString("\n")
        .replace("Action$1", "Action_1")
        .replace("{ queries, context, }", "options")
        .replace("{ ...options }", "options")
        .replace("{ pageParam, ...options }", "options")
        .replace("{ refetchPage, ...options }", "options")
        .replace("{ refetchPage, ...options }", "options")
        .replace(
            "const useQueryClient: (queryClient?: QueryClient) => QueryClient",
            "function useQueryClient(queryClient?: QueryClient): QueryClient"
        )
        .replace(
            "Omit<MutationObserverOptions<TData, TError, TVariables, TContext>, '_defaulted' | 'variables'>",
            "MutationObserverOptions<TData, TError, TVariables, TContext>"
        )
        .replace(
            "getQueryData<TQueryFnData = unknown, TTaggedQueryKey extends QueryKey = QueryKey, TInferredQueryFnData = TTaggedQueryKey extends DataTag<unknown, infer TaggedValue, unknown> ? TaggedValue : TQueryFnData>(queryKey: TTaggedQueryKey): TInferredQueryFnData | undefined;",
            "getQueryData<TQueryFnData>(queryKey: QueryKey): TQueryFnData | undefined;",
        )
        .replace(
            "setQueryData<TQueryFnData = unknown, TTaggedQueryKey extends QueryKey = QueryKey, TInferredQueryFnData = TTaggedQueryKey extends DataTag<unknown, infer TaggedValue, unknown> ? TaggedValue : TQueryFnData>(queryKey: TTaggedQueryKey, updater: Updater<NoInfer<TInferredQueryFnData> | undefined, NoInfer<TInferredQueryFnData> | undefined>, options?: SetDataOptions): TInferredQueryFnData | undefined;",
            "setQueryData<TQueryFnData>(queryKey: QueryKey, updater: Updater<TQueryFnData | undefined, TQueryFnData | undefined>, options?: SetDataOptions): TQueryFnData | undefined;",
        )
        .replace(
            "setQueriesData<TQueryFnData, TQueryFilters extends QueryFilters<any, any, any, any> = QueryFilters<TQueryFnData>, TInferredQueryFnData = TQueryFilters extends QueryFilters<infer TData, any, any, any> ? TData : TQueryFnData>(filters: TQueryFilters, updater: Updater<NoInfer<TInferredQueryFnData> | undefined, NoInfer<TInferredQueryFnData> | undefined>, options?: SetDataOptions): Array<[QueryKey, TInferredQueryFnData | undefined]>;",
            "setQueriesData<TQueryFnData, TQueryFilters extends QueryFilters<*, *, *, *>>(filters: TQueryFilters, updater: Updater<TQueryFnData?, TQueryFnData?>, options?: SetDataOptions): ReadonlyArray<JsTuple2<QueryKey, TQueryFnData?>>;",
        )
        .replace(
            "getQueriesData<TQueryFnData = unknown, TQueryFilters extends QueryFilters<any, any, any, any> = QueryFilters<TQueryFnData>, TInferredQueryFnData = TQueryFilters extends QueryFilters<infer TData, any, any, any> ? TData : TQueryFnData>(filters: TQueryFilters): Array<[QueryKey, TInferredQueryFnData | undefined]>;",
            "getQueriesData<TQueryFnData, TQueryFilters extends QueryFilters<*, *, *, *>>(filters: TQueryFilters): ReadonlyArray<JsTuple2<QueryKey, TQueryFnData?>>;",
        )
        .replace(
            "getQueryState<TQueryFnData = unknown, TError = DefaultError, TTaggedQueryKey extends QueryKey = QueryKey, TInferredQueryFnData = TTaggedQueryKey extends DataTag<unknown, infer TaggedValue, unknown> ? TaggedValue : TQueryFnData, TInferredError = TTaggedQueryKey extends DataTag<unknown, unknown, infer TaggedError> ? TaggedError extends UnsetMarker ? TError : TaggedError : TError>(queryKey: TTaggedQueryKey): QueryState<TInferredQueryFnData, TInferredError> | undefined;",
            "getQueryState<TQueryFnData, TError>(queryKey: QueryKey): QueryState<TQueryFnData, TError> | undefined;",
        )
        .replace(
            "isMutating<TMutationFilters extends MutationFilters<any, any> = MutationFilters>(filters?: TMutationFilters): number;",
            "isMutating<TMutationFilters extends MutationFilters<*, *, *, *> = MutationFilters>(filters?: TMutationFilters): number;",
        )
        .replace("\n    isDataEqual?: (oldData: TData | undefined, newData: TData) => boolean;\n", "\n")
        .replace(OPTIMISTIC_RESULT, "QueriesObserverOptimisticResult<TCombinedResult>")
        .replace(
            """
            type QueryPersister<T = unknown, TQueryKey extends QueryKey = QueryKey, TPageParam = never> = [TPageParam] extends [never] ? (queryFn: QueryFunction<T, TQueryKey, never>, context: QueryFunctionContext<TQueryKey>, query: Query) => T | Promise<T> : (queryFn: QueryFunction<T, TQueryKey, TPageParam>, context: QueryFunctionContext<TQueryKey>, query: Query) => T | Promise<T>;
            type QueryFunctionContext<TQueryKey extends QueryKey = QueryKey, TPageParam = never> = [TPageParam] extends [never] ? {
                client: QueryClient;
                queryKey: TQueryKey;
                signal: AbortSignal;
                meta: QueryMeta | undefined;
                pageParam?: unknown;
                /**
                 * @deprecated
                 * if you want access to the direction, you can add it to the pageParam
                 */
                direction?: unknown;
            } : {
                client: QueryClient;
                queryKey: TQueryKey;
                signal: AbortSignal;
                pageParam: TPageParam;
                /**
                 * @deprecated
                 * if you want access to the direction, you can add it to the pageParam
                 */
                direction: FetchDirection;
                meta: QueryMeta | undefined;
            };
            """.trimIndent(),
            """
            type QueryPersister<T = unknown, TQueryKey extends QueryKey = QueryKey, TPageParam> = (queryFn: QueryFunction<T, TQueryKey, TPageParam>, context: QueryFunctionContext<TQueryKey>, query: Query) => T | Promise<T>;
            interface QueryFunctionContext<TQueryKey extends QueryKey = QueryKey, TPageParam> {
                client: QueryClient;
                queryKey: TQueryKey;
                signal: AbortSignal;
                pageParam: TPageParam | undefined;
                meta: QueryMeta | undefined;
            }
            """.trimIndent(),
        )
        .replace(
            "interface ObserverFetchOptions extends FetchOptions",
            "interface ObserverFetchOptions<TData> extends FetchOptions<TData>",
        )
        // TODO: check
        .replace("    get meta(): ", "    meta: ")
        .replace("    get promise(): ", "    promise: ")
        // TEMP
        .replace(" & {\n        manual: boolean;\n    }", "")
        .replace(
            """
            subscribe: Subscribable<InfiniteQueryObserverListener<TData, TError>>['subscribe'];
            """.trimIndent(),
            """
            subscribe: (listener?: InfiniteQueryObserverListener<TData, TError>) -> () -> Unit  
            """.trimIndent()
        )
        .replace(
            """
            getCurrentResult: ReplaceReturnType<QueryObserver<TQueryFnData, TError, TData, InfiniteData<TQueryData, TPageParam>, TQueryKey>['getCurrentResult'], InfiniteQueryObserverResult<TData, TError>>;
            """.trimIndent(),
            """
            getCurrentResult: () -> InfiniteQueryObserverResult<TData, TError>
            """.trimIndent()
        )
        .replace(
            """
            protected fetch: ReplaceReturnType<QueryObserver<TQueryFnData, TError, TData, InfiniteData<TQueryData, TPageParam>, TQueryKey>['fetch'], Promise<InfiniteQueryObserverResult<TData, TError>>>;
            """.trimIndent(),
            """
            protected fetch: (fetchOptions: ObserverFetchOptions) -> Promise<InfiniteQueryObserverResult<TData, TError>>
            """.trimIndent()
        )
        .replace("type dataTagSymbol = typeof dataTagSymbol;", "")
        .replace("type dataTagErrorSymbol = typeof dataTagErrorSymbol;", "")
        .replace("Query<unknown, Error, unknown, readonly unknown[]>[]", "Query<unknown, Error, unknown, QueryKey>[]")
        .replace(
            "QueryObserver<unknown, Error, unknown, unknown, readonly unknown[]>[]",
            "QueryObserver<unknown, Error, unknown, unknown, QueryKey>[]"
        )
        .replace(
            "function defaultshouldRedactErrors(_: unknown): boolean;",
            "function defaultShouldRedactErrors(error: unknown): boolean;",
        )

    content = when (definitionFile.name) {
        "focusManager.d.ts" -> content.replace("SetupFn", "FocusManagerSetupFn")
        "onlineManager.d.ts" -> content.replace("SetupFn", "OnlineManagerSetupFn")

        "useIsFetching.d.ts" -> content.replace(" Options", " UseIsFetchingOptions")
        "useIsMutating.d.ts" -> content.replace(" Options", " UseIsMutatingOptions")

        else -> content
    }

    return getBlocks(content.split("\n"))
        .asSequence()
        .mapNotNull { (keyword, source) ->
            when (keyword) {
                JsTypeKeyword.CONST -> Const(source)
                JsTypeKeyword.FUNCTION -> Function(source)
                JsTypeKeyword.TYPE -> Type(source, fixAction)
                JsTypeKeyword.INTERFACE -> Interface(source, fixAction)
                JsTypeKeyword.CLASS -> Class(source)
                else -> null
            }
        }
        .filter { it.name != "Optional" }
        .filter { it.name != "WithRequired" }
        .toList()
}

private fun getBlocks(
    lines: List<String>,
): List<Pair<String, String>> {
    val result = mutableListOf<Pair<String, String>>()

    var index = 0
    while (index < lines.size) {
        val line = lines[index]

        val key = line.substringBefore(" ")
        if (key in DECLARE_KEYWORDS || key in TYPE_KEYWORDS) {
            val keyword = TYPE_KEYWORDS
                .firstOrNull { "$it " in line }

            when (keyword) {
                JsTypeKeyword.CONST,
                JsTypeKeyword.FUNCTION,
                JsTypeKeyword.TYPE,
                    -> result.add(keyword to line)

                JsTypeKeyword.INTERFACE,
                JsTypeKeyword.CLASS,
                    -> {
                    val startIndex = index
                    while (lines[++index] != "}");
                    val body = lines.subList(startIndex, index + 1).joinToString("\n")
                    result.add(keyword to body)
                }
            }
        }

        index++
    }

    return result.toList()
}
