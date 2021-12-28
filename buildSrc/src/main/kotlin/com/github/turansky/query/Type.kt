package com.github.turansky.query

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

            "|" in body -> "Union /* $body */"

            name.endsWith("Result") -> body

            body == "(...args: any[]) => void" -> "Function<Unit>"

            body.startsWith("(") && "..." !in body -> {
                kotlinFunctionType(body)
                    .replace("QueryObserverResult[]", "Array<out QueryObserverResult<*, *>>")
                    .replace("TQueryFnData[]", "Array<out TQueryFnData>")

                    .replace("mutation: Mutation", "mutation: Mutation<*, *, *, *>")
                    .replace("mutation?: Mutation", "mutation: Mutation<*, *, *, *>?")
                    .replace("event?: QueryCacheNotifyEvent", "event: QueryCacheNotifyEvent?")
                    .replace("options?: MutateOptions<TData, TError, TVariables, TContext>", "options: MutateOptions<TData, TError, TVariables, TContext>?")
            }

            else -> "Any"
        }
    }

    override fun toCode(): String {
        if (name == "Override")
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
            val parentDeclaration = declaration.replace("Result<", "BaseResult<")
            return "external sealed interface $declaration\n: $parentDeclaration"
        }

        return "typealias $name${formatParameters(typeParameters)} = $body"
    }
}
