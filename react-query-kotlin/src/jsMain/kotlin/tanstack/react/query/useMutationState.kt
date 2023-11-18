// Automatically generated - do not modify!

@file:JsModule("@tanstack/react-query")

@file:Suppress(
    "UNUSED_TYPEALIAS_PARAMETER",
    "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
)

package tanstack.react.query

import js.core.ReadonlyArray
import tanstack.query.core.MutationFilters
import tanstack.query.core.QueryClient

external fun useIsMutating(
    filters: MutationFilters = definedExternally,
    queryClient: QueryClient = definedExternally,
): Int

typealias MutationStateOptions<TResult> = Any

external fun <TResult> useMutationState(
    options: MutationStateOptions<TResult> = definedExternally,
    queryClient: QueryClient = definedExternally,
): ReadonlyArray<TResult>
