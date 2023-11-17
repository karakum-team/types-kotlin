// Automatically generated - do not modify!

@file:JsModule("@tanstack/react-query")

package tanstack.react.query

import tanstack.query.core.HydrateOptions
import tanstack.query.core.QueryClient

external interface HydrationBoundaryProps : react.PropsWithChildren {
    var state: Any?
    var options: HydrateOptions
    fun
}(}: } ): }
override var children: react.ReactNode?
var queryClient: QueryClient
}

external val HydrationBoundary: (

{ children, options, state, queryClient, }: HydrationBoundaryProps) -> React.ReactElement<any, string | React.JSXElementConstructor<any>>
