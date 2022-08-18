// Automatically generated - do not modify!

@file:JsModule("react-query")
@file:JsNonModule

package tanstack.react.query

import tanstack.query.core.MutationFilters
import tanstack.query.core.MutationKey

external interface Options : ContextOptions

external fun useIsMutating(
    filters: MutationFilters = definedExternally,
    options: Options = definedExternally,
): Int

external fun useIsMutating(
    mutationKey: MutationKey = definedExternally,
    filters: MutationFilters = definedExternally,
    options: Options = definedExternally,
): Int
