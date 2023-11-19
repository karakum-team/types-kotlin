// Automatically generated - do not modify!

@file:JsModule("@tanstack/query-core")

package tanstack.query.core

import js.core.ReadonlyArray

external interface DehydrateOptions {
    var shouldDehydrateMutation: (mutation: Mutation<*, *, *, *>) -> Boolean
    var shouldDehydrateQuery: (query: Query<*, *, *, *>) -> Boolean
}

external interface DefaultHydrateOptions {
    var queries: QueryOptions<*, *, *, *, *>
    var mutations: MutationOptions<*, *, *, *>
}

external interface HydrateOptions {
    var defaultOptions: DefaultHydrateOptions
}

external interface DehydratedMutation {
    var mutationKey: MutationKey
    var state: MutationState<*, *, *, *>
    var meta: MutationMeta
}

external interface DehydratedQuery {
    var queryHash: String
    var queryKey: QueryKey
    var state: QueryState<*, *>
    var meta: QueryMeta
}

external interface DehydratedState {
    var mutations: ReadonlyArray<DehydratedMutation>
    var queries: ReadonlyArray<DehydratedQuery>
}

external fun defaultShouldDehydrateMutation(mutation: Mutation<*, *, *, *>): Boolean

external fun defaultShouldDehydrateQuery(query: Query<*, *, *, *>): Boolean

external fun dehydrate(
    client: QueryClient,
    options: DehydrateOptions = definedExternally,
): DehydratedState

external fun hydrate(
    client: QueryClient,
    dehydratedState: Any?,
    options: HydrateOptions = definedExternally,
)
