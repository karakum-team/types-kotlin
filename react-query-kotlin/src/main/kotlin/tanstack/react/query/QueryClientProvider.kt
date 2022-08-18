// Automatically generated - do not modify!

@file:JsModule("react-query")
@file:JsNonModule

@file:Suppress(
    "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
)

package tanstack.react.query

import tanstack.query.core.*

external val defaultContext: react.Context<QueryClient?>

external val useQueryClient: (options: ContextOptions?) -> QueryClient

typealias QueryClientProviderPropsBase = Any

typealias QueryClientProviderPropsWithContext = Any

typealias QueryClientProviderPropsWithContextSharing = Any

typealias QueryClientProviderProps = Union /* QueryClientProviderPropsWithContext | QueryClientProviderPropsWithContextSharing */

external val QueryClientProvider: (props: QueryClientProviderProps) -> JSX.Element
