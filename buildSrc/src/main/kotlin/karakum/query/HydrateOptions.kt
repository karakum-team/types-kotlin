package karakum.query

// language=TypeScript
internal val HYDRATE_OPTIONS_SOURCE = """
interface HydrateOptions {
    defaultOptions?: {
        queries?: QueryOptions;
        mutations?: MutationOptions;
    };
}
""".trimIndent()

// language=kotlin
internal val HYDRATE_OPTIONS_CODE = """
external interface DefaultHydrateOptions {
    var queries: QueryOptions<*, *, *, *>
    var mutations: MutationOptions<*, *, *, *>
}    
    
external interface HydrateOptions {
    var defaultOptions: DefaultHydrateOptions
}
""".trimIndent()
