package karakum.query

// language=TypeScript
internal val HYDRATE_OPTIONS_SOURCE = """
interface HydrateOptions {
    defaultOptions?: {
        deserializeData?: TransformerFn;
        queries?: QueryOptions;
        mutations?: MutationOptions<unknown, DefaultError, unknown, unknown>;
    };
}
""".trimIndent()

// language=kotlin
internal val HYDRATE_OPTIONS_CODE = """
external interface DefaultHydrateOptions {
    var deserializeData: TransformerFn?
    var queries: QueryOptions<*, *, *, *, *>?
    var mutations: MutationOptions<*, DefaultError, *, *>?
}    
    
external interface HydrateOptions {
    var defaultOptions: DefaultHydrateOptions?
}
""".trimIndent()
