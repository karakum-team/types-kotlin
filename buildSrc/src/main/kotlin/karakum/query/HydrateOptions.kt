package karakum.query

// language=TypeScript
internal val HYDRATE_OPTIONS_SOURCE = """
interface HydrateOptions {
    defaultOptions?: {
        transformPromise?: (promise: Promise<any>) => Promise<any>;
        queries?: QueryOptions;
        mutations?: MutationOptions<unknown, DefaultError, unknown, unknown>;
    };
}
""".trimIndent()

// language=kotlin
internal val HYDRATE_OPTIONS_CODE = """
external interface DefaultHydrateOptions {
    var queries: QueryOptions<*, *, *, *, *>
    var mutations: MutationOptions<*, *, *, *>
}    
    
external interface HydrateOptions {
    var defaultOptions: DefaultHydrateOptions
}
""".trimIndent()
