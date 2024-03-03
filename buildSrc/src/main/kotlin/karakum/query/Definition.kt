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
            "getQueryData<TQueryFnData = unknown, TTaggedQueryKey extends QueryKey = QueryKey, TInferredQueryFnData = TTaggedQueryKey extends DataTag<unknown, infer TaggedValue> ? TaggedValue : TQueryFnData>(queryKey: TTaggedQueryKey): TInferredQueryFnData | undefined;",
            "getQueryData<TQueryFnData>(queryKey: QueryKey): TQueryFnData | undefined;",
        )
        .replace(
            "setQueryData<TQueryFnData = unknown, TTaggedQueryKey extends QueryKey = QueryKey, TInferredQueryFnData = TTaggedQueryKey extends DataTag<unknown, infer TaggedValue> ? TaggedValue : TQueryFnData>(queryKey: TTaggedQueryKey, updater: Updater<NoInfer<TInferredQueryFnData> | undefined, NoInfer<TInferredQueryFnData> | undefined>, options?: SetDataOptions): TInferredQueryFnData | undefined;",
            "setQueryData<TQueryFnData>(queryKey: QueryKey, updater: Updater<TQueryFnData | undefined, TQueryFnData | undefined>, options?: SetDataOptions): TQueryFnData | undefined;",
        )
        .replace(
            "getQueryState<TQueryFnData = unknown, TError = DefaultError, TTaggedQueryKey extends QueryKey = QueryKey, TInferredQueryFnData = TTaggedQueryKey extends DataTag<unknown, infer TaggedValue> ? TaggedValue : TQueryFnData>(queryKey: TTaggedQueryKey): QueryState<TInferredQueryFnData, TError> | undefined;",
            "getQueryState<TQueryFnData, TError>(queryKey: QueryKey): QueryState<TQueryFnData, TError> | undefined;",
        )
        .replace("\n    isDataEqual?: (oldData: TData | undefined, newData: TData) => boolean;\n", "\n")
        .replace(OPTIMISTIC_RESULT, "QueriesObserverOptimisticResult<TCombinedResult>")
        .replace(
            """
            type QueryPersister<T = unknown, TQueryKey extends QueryKey = QueryKey, TPageParam = never> = [TPageParam] extends [never] ? (queryFn: QueryFunction<T, TQueryKey, never>, context: QueryFunctionContext<TQueryKey>, query: Query) => T | Promise<T> : (queryFn: QueryFunction<T, TQueryKey, TPageParam>, context: QueryFunctionContext<TQueryKey>, query: Query) => T | Promise<T>;
            type QueryFunctionContext<TQueryKey extends QueryKey = QueryKey, TPageParam = never> = [TPageParam] extends [never] ? {
                queryKey: TQueryKey;
                signal: AbortSignal;
                meta: QueryMeta | undefined;
            } : {
                queryKey: TQueryKey;
                signal: AbortSignal;
                pageParam: TPageParam;
                direction: FetchDirection;
                meta: QueryMeta | undefined;
            };
            """.trimIndent(),
            """
            type QueryPersister<T = unknown, TQueryKey extends QueryKey = QueryKey, TPageParam> = (queryFn: QueryFunction<T, TQueryKey, TPageParam>, context: QueryFunctionContext<TQueryKey>, query: Query) => T | Promise<T>;
            interface QueryFunctionContext<TQueryKey extends QueryKey = QueryKey, TPageParam> {
                queryKey: TQueryKey;
                signal: AbortSignal;
                pageParam: TPageParam | undefined;
                direction: FetchDirection | undefined;
                meta: QueryMeta | undefined;
            }
            """.trimIndent(),
        )
        // TODO: check
        .replace("    get meta(): ", "    meta: ")
        // TEMP
        .replace(" & {\n        manual: boolean;\n    }", "")

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
