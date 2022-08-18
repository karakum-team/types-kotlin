// Automatically generated - do not modify!

@file:JsModule("react-query")
@file:JsNonModule

package tanstack.react.query

import tanstack.query.core.QueryClient

external val defaultContext: react.Context<QueryClient?>

external val useQueryClient: (options: ContextOptions?) -> QueryClient

external interface QueryClientProviderProps : react.PropsWithChildren {
    var client: QueryClient
}

external val QueryClientProvider: react.FC<QueryClientProviderProps>
