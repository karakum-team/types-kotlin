// Automatically generated - do not modify!

@file:JsModule("react-query")
@file:JsNonModule

package tanstack.react.query

import tanstack.query.core.QueryFilters
import tanstack.query.core.QueryKey

external interface Options : ContextOptions

external fun useIsFetching(
    filters: QueryFilters = definedExternally,
    options: Options = definedExternally,
): Int

external fun useIsFetching(
    queryKey: QueryKey = definedExternally,
    filters: QueryFilters = definedExternally,
    options: Options = definedExternally,
): Int
