package karakum.query

private val SPECIAL_TYPES = setOf(
    "boolean | number | ShouldRetryFunction<TError>",
    "number | RetryDelayFunction<TError>",
    "TOutput | DataUpdateFunction<TInput, TOutput>",
)

class Type(
    override val source: String,
    fixAction: Boolean,
) : Declaration() {
    private val typeParameters: List<String> by lazy {
        if ("> = " !in source) {
            return@lazy emptyList<String>()
        }

        parseTypeParameters(source.substringBefore("> = ") + ">")
            .map { it.substringBefore(": ") }
    }

    override val name: String =
        getTypeName(source, JsTypeKeyword.TYPE, fixAction)

    val enumMode: Boolean by lazy {
        " = '" in source
    }

    private val originalBody: String by lazy {
        val result = source
            .substringAfterLast(" = ")
            .removeSuffix(";")
            .replace(" | undefined", "?")

        if (result.startsWith("Override<")) {
            result.removePrefix("Override<")
                .substringBefore(", {")
        } else result
    }

    private val body: String by lazy {
        val body = originalBody
            .replace(" => T | Promise<T>", " => Promise<T>")
            .replace("QueryFunctionContext<TQueryKey>", "QueryFunctionContext<TQueryKey, *>")

        when {
            body in SPECIAL_TYPES -> body.substringAfterLast(" | ")

            body.toIntOrNull() != null -> body

            name == QUERY_KEY -> body

            name == "UseErrorBoundary" -> body
                .removeSurrounding("boolean | (", ")")
                .replace(" => boolean", " -> Boolean")

            "|" in body -> "Union /* $body */"

            name.endsWith("Result") -> body

            body == "Record<string, unknown>" -> "Record<String, *>"
            body.startsWith("MutateFunction<") -> body

            body == "(...args: any[]) => void" -> "Function<Unit>"

            body.startsWith("(") && "..." in body -> "Function<Unit> /* $body */"

            body.startsWith("(") -> {
                kotlinFunctionType(body)
                    .replace("QueryObserverResult[]", "ReadonlyArray<QueryObserverResult<*, *>>")
                    .replace("TQueryFnData[]", "ReadonlyArray<TQueryFnData>")

                    .replace("mutation: Mutation", "mutation: Mutation<*, *, *, *>")
                    .replace("mutation?: Mutation", "mutation: Mutation<*, *, *, *>?")
                    .replace("event?: QueryCacheNotifyEvent", "event: QueryCacheNotifyEvent?")
                    .replace(
                        "options?: MutateOptions<TData, TError, TVariables, TContext>",
                        "options: MutateOptions<TData, TError, TVariables, TContext>?"
                    )
            }

            else -> "Any"
        }
    }

    override fun toCode(): String {
        if (name == "Override")
            return ""

        if (name == "QueryClientProviderProps")
            return """
                external interface QueryClientProviderProps: react.PropsWithChildren {
                    var client: QueryClient
                }
            """.trimIndent()

        if (name.startsWith("QueryClientProviderProps"))
            return ""

        if (originalBody.startsWith("'")) {
            val items = originalBody.splitToSequence(" | ")
                .map { it.removePrefix("'") }
                .map { it.removeSuffix("'") }
                .toList()

            val jsName = items.joinToString(", ", "({ ", " })") { "${it.toUpperCase()}: '$it'" }

            return sequenceOf(
                "@Suppress(\"NAME_CONTAINS_ILLEGAL_CHARS\")",
                "// language=JavaScript",
                "@JsName(\"\"\"$jsName\"\"\")",
                "external enum class $name {",
                items.joinToString("", postfix = "\n;") { "${it.toUpperCase()},\n" },
                "}"
            ).joinToString("\n")
        }

        val declaration = "$name${formatParameters(typeParameters)}"
        if (name.endsWith("Result") && " | " in source) {
            val parentDeclaration = declaration
                .removePrefix("Defined")
                .replace("Result<", "BaseResult<")

            val modifiers = if (name == "MutationObserverResult") "" else "sealed"
            return "external $modifiers interface $declaration\n: $parentDeclaration"
        }

        if (body.toIntOrNull() != null)
            return "const val $name = $body"

        if (name == QUERY_KEY)
            return """
                // $body 
                external interface $name
                """.trimIndent()

        if (name == "UseBaseMutationResult") {
            return sequenceOf(
                "external interface $name${formatParameters(typeParameters)} : $body {",
                "    // override val mutate: UseMutateFunction<TData, TError, TVariables, TContext>",
                "    val mutateAsync: UseMutateAsyncFunction<TData, TError, TVariables, TContext>",
                "}",
            ).joinToString("\n")
        }

        return "typealias $name${formatParameters(typeParameters)} = $body"
    }
}
