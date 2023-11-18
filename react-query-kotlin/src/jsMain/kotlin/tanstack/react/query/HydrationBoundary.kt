// Automatically generated - do not modify!

@file:JsModule("@tanstack/react-query")

package tanstack.react.query

import tanstack.query.core.HydrateOptions
import tanstack.query.core.QueryClient

external interface HydrationBoundaryProps : react.PropsWithChildren {
    var state: Any?
    var options: HydrateOptions /* Omit<HydrateOptions, 'defaultOptions'> & { defaultOptions?: Omit<HydrateOptions['defaultOptions'], 'mutations'>; } */
    override var children: react.ReactNode?
    var queryClient: QueryClient
}

external val HydrationBoundary: react.FC<HydrationBoundaryProps>
