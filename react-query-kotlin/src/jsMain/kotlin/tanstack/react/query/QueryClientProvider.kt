// Automatically generated - do not modify!

@file:JsModule("@tanstack/react-query")

package tanstack.react.query

import tanstack.query.core.QueryClient

external val QueryClientContext: react.Context<QueryClient?>

external val useQueryClient: (queryClient: QueryClient?) -> QueryClient

external interface QueryClientProviderProps : react.PropsWithChildren {
    var client: QueryClient
}

external val QueryClientProvider: react.FC<QueryClientProviderProps>
