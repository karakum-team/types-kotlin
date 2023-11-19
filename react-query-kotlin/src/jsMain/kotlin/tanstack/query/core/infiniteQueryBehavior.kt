// Automatically generated - do not modify!

@file:JsModule("@tanstack/query-core")

package tanstack.query.core

external fun <TQueryFnData, TError, TData, TPageParam> infiniteQueryBehavior(pages: Int = definedExternally): QueryBehavior<TQueryFnData, TError, InfiniteData<TData, TPageParam>, *>

external fun hasNextPage(
    options: InfiniteQueryPageParamsOptions<*, *>,
    data: InfiniteData<*, *> = definedExternally,
): Boolean

external fun hasPreviousPage(
    options: InfiniteQueryPageParamsOptions<*, *>,
    data: InfiniteData<*, *> = definedExternally,
): Boolean
