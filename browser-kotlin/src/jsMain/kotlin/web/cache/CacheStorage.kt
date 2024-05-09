// Automatically generated - do not modify!

package web.cache

import js.array.ReadonlyArray
import js.promise.Promise
import web.http.Request
import web.http.Response
import web.url.URL

/**
 * The storage for Cache objects.
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage)
 */
sealed external class CacheStorage {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage/delete)
     */
    @JsName("delete")
    fun deleteAsync(cacheName: String): Promise<Boolean>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage/has)
     */
    @JsName("has")
    fun hasAsync(cacheName: String): Promise<Boolean>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage/keys)
     */
    @JsName("keys")
    fun keysAsync(): Promise<ReadonlyArray<String>>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage/match)
     */
    @JsName("match")
    fun matchAsync(
        request: Request,
        options: MultiCacheQueryOptions = definedExternally,
    ): Promise<Response?>

    @JsName("match")
    fun matchAsync(
        request: String,
        options: MultiCacheQueryOptions = definedExternally,
    ): Promise<Response?>

    @JsName("match")
    fun matchAsync(
        request: URL,
        options: MultiCacheQueryOptions = definedExternally,
    ): Promise<Response?>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/CacheStorage/open)
     */
    @JsName("open")
    fun openAsync(cacheName: String): Promise<Cache>
}
